package com.pzj.schoolrun.service.impl;
import com.github.benmanes.caffeine.cache.Cache;
import com.pzj.schoolrun.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 同步骑手位置到订单信息
 */
@Service
public class CourierLocationServiceImpl {

    @Autowired
    private IOrdersService orderService;

    @Autowired
    private Cache<String, Object> caffeineCache;

    // 模拟内存中的订单位置映射 <orderId, latLng>
    private final Map<Long, double[]> orderLocationMap = new ConcurrentHashMap<>();

    /**
     * 每分钟执行一次，更新所有骑手相关的订单位置
     */
    @Scheduled(fixedRate = 60000)
    public void updateOrderLocations() {
        // 获取当前所有缓存中的骑手ID（示例可扩展为从数据库获取活跃骑手）
        List<Long> courierIds = getAllCachedCourierIds();

        for (Long courierId : courierIds) {
            String locationKey = "courierId:" + courierId;
            Object locationObj = caffeineCache.getIfPresent(locationKey);

            if (locationObj != null) {
                String[] latLng = locationObj.toString().split(",");
                Double latitude = Double.parseDouble(latLng[0]);
                Double longitude = Double.parseDouble(latLng[1]);

                // 获取与此骑手相关的进行中订单ID列表
                List<Long> orderIds = orderService.getOngoingOrderIdsByCourierId(courierId);

                for (Long orderId : orderIds) {
                    orderLocationMap.put(orderId, new double[]{latitude, longitude});
                }
            }
        }
    }

    /**
     * 获取所有在缓存中存在的骑手ID（可根据业务扩展为从DB获取）
     */
    private List<Long> getAllCachedCourierIds() {
        // 示例：实际应根据项目逻辑调整
        return List.of(1L, 2L, 3L);
    }

    /**
     * 获取指定订单的最新位置（供其他服务调用）
     */
    public double[] getOrderLocation(Long orderId) {
        return orderLocationMap.getOrDefault(orderId, null);
    }
}
