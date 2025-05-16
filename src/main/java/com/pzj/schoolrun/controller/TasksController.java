package com.pzj.schoolrun.controller;

import com.github.pagehelper.PageInfo;
import com.pzj.schoolrun.entity.Couriers;
import com.pzj.schoolrun.entity.Orders;
import com.pzj.schoolrun.entity.Tasks;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.tasks.TasksAddVO;
import com.pzj.schoolrun.model.vo.tasks.TasksUpdateVO;
import com.pzj.schoolrun.service.IOrdersService;
import com.pzj.schoolrun.service.ITasksService;
import com.pzj.schoolrun.service.IUsersService;
import com.pzj.schoolrun.service.impl.CouriersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 跑腿任务表 前端控制器
 * </p>
 *
 * @author
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/tasks")
public class TasksController extends BaseController {
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private IOrdersService ordersService; // 注入订单服务
    @Autowired
    private CouriersServiceImpl couriersService;
    @Autowired
    private IUsersService usersService;

    @GetMapping("/all")
    public Result<?> allList() {
        startPage();
        List<Tasks> list = tasksService.list();
        return Result.success(PageInfo.of(list));
    }

    @GetMapping("/myPublic")
    public Result<?> list() {
        startPage();
        Long userId = getUserId();

        List<Tasks> list = tasksService.getByUserId(userId);
        return Result.success(PageInfo.of(list));
    }

    @GetMapping("/getOneById")
    public Result<?> getOneById(@RequestParam Long taskId) {
        Tasks tasks = tasksService.getById(taskId);
        return Result.success(tasks);
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody TasksAddVO tasksAddVO) {
        try {
            Long userId = getUserId();
            BigDecimal commission = tasksAddVO.getPrice();

            // 创建任务对象
            Tasks tasks = Tasks.builder()
                    .userId(userId)
                    .taskType(tasksAddVO.getTaskType())
                    .title(tasksAddVO.getTitle())
                    .description(tasksAddVO.getDescription())
                    .pickupLocation(tasksAddVO.getPickupLocation())
                    .pickupCoordinates(tasksAddVO.getPickupCoordinates())
                    .deliveryLocation(tasksAddVO.getDeliveryLocation())
                    .deliveryCoordinates(tasksAddVO.getDeliveryCoordinates())
                    .deadline(tasksAddVO.getDeadline())
                    .imagesUrl(tasksAddVO.getImagesUrl())
                    .price(commission)
                    .status(0)
                    .paymentStatus(0)
                    .remark(tasksAddVO.getRemark())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            // 保存任务
            tasksService.save(tasks);

            // 扣除用户佣金（支付）
            return usersService.payCommission(userId, commission);

        } catch (Exception e) {
            return Result.error("任务发布失败: " + e.getMessage());
        }
    }



    @PostMapping("/update")
    public Result<?> update(@RequestBody TasksUpdateVO tasksUpdateVO) {
        try {
            // 获取原任务信息
            Tasks oldTask = tasksService.getById(tasksUpdateVO.getTaskId());
            if (oldTask == null) {
                return Result.error("任务不存在");
            }

            // 获取当前登录用户ID
            Long userId = getUserId();

            // 构建新的任务对象
            BigDecimal newPrice = tasksUpdateVO.getPrice();
            BigDecimal oldPrice = oldTask.getPrice();

            Tasks updatedTask = Tasks.builder()
                    .taskId(tasksUpdateVO.getTaskId())
                    .taskType(tasksUpdateVO.getTaskType())
                    .title(tasksUpdateVO.getTitle())
                    .description(tasksUpdateVO.getDescription())
                    .pickupLocation(tasksUpdateVO.getPickupLocation())
                    .pickupCoordinates(tasksUpdateVO.getPickupCoordinates())
                    .deliveryLocation(tasksUpdateVO.getDeliveryLocation())
                    .deliveryCoordinates(tasksUpdateVO.getDeliveryCoordinates())
                    .deadline(tasksUpdateVO.getDeadline())
                    .remark(tasksUpdateVO.getRemark())
                    .updatedAt(LocalDateTime.now())
                    .build();

            // 更新任务信息
            tasksService.updateById(updatedTask);

            // =============================
            // 💰 多退少补逻辑
            // =============================
            if (newPrice.compareTo(oldPrice) > 0) {
                // 用户需要补差价
                BigDecimal diff = newPrice.subtract(oldPrice);
                return usersService.payCommission(userId, diff);
            } else if (newPrice.compareTo(oldPrice) < 0) {
                // 用户多付了，系统退还差价
                BigDecimal diff = oldPrice.subtract(newPrice);
                return usersService.rechargeBalance(userId, diff);
            }

            return Result.success();

        } catch (Exception e) {
            return Result.error("任务更新失败: " + e.getMessage());
        }
    }


    //状态(0=待接单,1=已接单,2=进行中,3=已完成,4=已取消)

    @PostMapping("/cancel")
    public Result<?> cancel(@RequestParam Long taskId) {
        try {
            Tasks task = tasksService.getById(taskId);
            if (task == null) {
                return Result.error("任务不存在");
            }

            // 获取任务发布者ID和佣金金额
            Long userId = task.getUserId();
            BigDecimal commission = task.getPrice();

            // 更新任务状态为“已取消”
            task.setStatus(4); //  4 表示已取消
            tasksService.updateById(task);

            // 退还佣金给用户
            return usersService.rechargeBalance(userId, commission);

        } catch (Exception e) {
            return Result.error("任务取消失败: " + e.getMessage());
        }
    }

    @PostMapping("/acceptTask")
    public Result<?> acceptTask(@RequestParam Long taskId) {
        Long userId = getUserId();

        // 从couriers表中查询courierId
        Couriers couriers = couriersService.query().eq("user_id", userId).one();
        if (couriers == null) {
            return Result.error("跑腿员信息不存在");
        }
        Long courierId = couriers.getCourierId();

        // 更新任务状态为已接单，并设置 courierId
        boolean success = tasksService.updateTaskStatus(taskId, 1, courierId); // 1 表示已接单
        if (!success) {
            return Result.error("任务不存在");
        }

        // =============================
        // 🔧 新增：创建对应的订单记录
        // =============================
        Orders order = Orders.builder()
                .taskId(taskId)
                .courierId(courierId)
                .orderStatus(1) // 待完成
                .createdAt(LocalDateTime.now())
                .build();

        boolean saveResult = ordersService.save(order);
        if (!saveResult) {
            return Result.error("任务接单成功但生成订单失败");
        }

        return Result.success();
    }


    //我接取的任务
    @GetMapping("/myAccept")
    public Result<?> myAccept() {
        Long userId = getUserId();

        // 从couriers表中查询courierId
        Couriers couriers = couriersService.query().eq("user_id", userId).one();
        if (couriers == null) {
            return Result.error("跑腿员信息不存在");
        }
        Long courierId = couriers.getCourierId();

        // 根据courierId查询任务列表
        List<Tasks> tasksList = tasksService.getByCourierId(courierId);

        return Result.success(tasksList);
    }


}
