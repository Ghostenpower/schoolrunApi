package com.pzj.schoolrun.controller;

import com.pzj.schoolrun.entity.Feedback;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.feedback.FeedbackVO;
import com.pzj.schoolrun.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 反馈信息管理控制器
 * </p>
 *
 * @author your_name
 * @since 2025-04-10
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 提交反馈信息
     */


    @PostMapping("/submit")
    public Result<?> submitFeedback(@RequestBody FeedbackVO feedbackVo) {
        try {
            Feedback feedback = Feedback.builder()
                    .suggestion(feedbackVo.getSuggestion())
                    .contact(feedbackVo.getContact())
                    .imagesUrl(feedbackVo.getImagesUrl())
                    .createTime(java.time.LocalDateTime.now())
                    .build();
            boolean success = feedbackService.submitFeedback(feedback);
            if (success) {
                return Result.success("反馈提交成功");
            } else {
                return Result.error("反馈提交失败");
            }
        } catch (Exception e) {
            return Result.error("反馈提交异常：" + e.getMessage());
        }
    }



    /**
     * 获取所有反馈信息列表
     */
    @GetMapping("/list")
    public Result<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        return Result.success(feedbackList);
    }

    /**
     * 根据ID获取单条反馈信息
     */
    @GetMapping("/detail/{id}")
    public Result<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback == null) {
            return Result.error(404, "未找到该反馈信息");
        }
        return Result.success(feedback);
    }
}
