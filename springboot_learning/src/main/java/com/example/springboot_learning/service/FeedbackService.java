package com.example.springboot_learning.service;

import com.example.springboot_learning.model.feedback.FeedbackInfo;
import com.example.springboot_learning.model.feedback.FeedbackParamInfo;
import com.example.springboot_learning.pojo.Feedback;

import java.util.List;

public interface FeedbackService {
    
    int addFeedback(Feedback feedback);

    int updateFeedback(Feedback feedback);

    FeedbackInfo selectFeedbackByFeedbackId(String feedbackId);

    int disabledFeedback(String feedbackId);

    List<FeedbackInfo> selectAllFeedbackList();

    List<FeedbackInfo> selecFeedbackByFeedbackParamInfo(FeedbackParamInfo feedbackParamInfo);

    int deleteFeedbackByFeedbackId(String feedbackId);
}
