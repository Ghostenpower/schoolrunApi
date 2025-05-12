package com.pzj.schoolrun.service;

import com.pzj.schoolrun.entity.Feedback;

import java.util.List;

public interface IFeedbackService {
    boolean submitFeedback(Feedback feedback);

    List<Feedback> getAllFeedback();

    Feedback getFeedbackById(Long id);
}