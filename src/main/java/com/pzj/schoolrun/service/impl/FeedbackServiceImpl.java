package com.pzj.schoolrun.service.impl;

import com.pzj.schoolrun.entity.Feedback;
import com.pzj.schoolrun.mapper.FeedbackMapper;
import com.pzj.schoolrun.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public boolean submitFeedback(Feedback feedback) {
        int rows = feedbackMapper.insert(feedback);
        return rows > 0;
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackMapper.selectAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackMapper.selectById(id);
    }
}
