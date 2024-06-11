package com.example.springboot_learning.mapper;

import com.example.springboot_learning.model.feedback.FeedbackParamInfo;
import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.pojo.Feedback;

import java.util.List;

public interface FeedbackMapper {

    int addFeedback(Feedback feedback);

    int updateFeedback(Feedback feedback);

    int disabledFeedback(String feedbackId);

    List<Feedback> selectAllFeedbackList(PageParamsInfo pageParamsInfo);

    Feedback selectFeedbackByFeedbackId(String feedbackId);

    List<Feedback> selecFeedbackByFeedbackParamInfo(FeedbackParamInfo feedbackParamInfo);

    int deleteFeedbackByFeedbackId(String feedbackId);
}
