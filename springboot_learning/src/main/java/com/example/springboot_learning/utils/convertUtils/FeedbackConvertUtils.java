package com.example.springboot_learning.utils.convertUtils;

import com.example.springboot_learning.model.feedback.FeedbackInfo;
import com.example.springboot_learning.pojo.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackConvertUtils {

    /**
     * 将Feedback数据库获取的实体对象转成对应处理后接口要返回的实体数据
     * @param feedback
     * @return feedbackInfo
     */
    public static FeedbackInfo feedbackInfoByFeedback(Feedback feedback) {

        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setId(feedback.getId());
        feedbackInfo.setFeedbackId(feedback.getFeedbackId());
        feedbackInfo.setTitle(feedback.getTitle());
        feedbackInfo.setContent(feedback.getContent());
        feedbackInfo.setQuestionTypeId(feedback.getQuestionTypeId());
        feedbackInfo.setCreateUser(feedback.getCreateUser());
        feedbackInfo.setContactTel(feedback.getContactTel());
        feedbackInfo.setContactEmail(feedback.getContactEmail());
        feedbackInfo.setImageUrls(feedback.getImageUrls());
        feedbackInfo.setSort(feedback.getSort());
        feedbackInfo.setStatus(feedback.getStatus());
        feedbackInfo.setCreateTime(feedback.getCreateTime());
        feedbackInfo.setUpdateTime(feedback.getUpdateTime());

        return feedbackInfo;
    }

    /**
     * 将Feedback数据库获取的实体对象列表转成对应处理后接口要返回的实体数据列表
     * @param feedbackList
     * @return feedbackInfoList
     */
    public static List<FeedbackInfo> feedbackInfoListByFeedbackList(List<Feedback> feedbackList) {

        List<FeedbackInfo> feedbackInfoList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            FeedbackInfo feedbackInfo = feedbackInfoByFeedback(feedback);
            feedbackInfoList.add(feedbackInfo);
        }
        return feedbackInfoList;
    }
    
}
