package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.FeedbackMapper;
import com.example.springboot_learning.model.feedback.FeedbackInfo;
import com.example.springboot_learning.model.feedback.FeedbackParamInfo;
import com.example.springboot_learning.pojo.Feedback;
import com.example.springboot_learning.service.FeedbackService;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import com.example.springboot_learning.utils.convertUtils.FeedbackConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public int addFeedback(Feedback feedback) {
        feedback.setFeedbackId(CommonUtils.getUUID());
        feedback.setSort(0);
        feedback.setStatus(0);
        feedback.setCreateTime(new Date());
        feedback.setUpdateTime(new Date());

        return feedbackMapper.addFeedback(feedback);
    }

    @Override
    public int updateFeedback(Feedback feedback) {
        if (feedback.getFeedbackId() == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        Feedback updateFeedback = feedbackMapper.selectFeedbackByFeedbackId(feedback.getFeedbackId());
        if (updateFeedback == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        feedback.setUpdateTime(new Date());
        return feedbackMapper.updateFeedback(feedback);
    }

    @Override
    public FeedbackInfo selectFeedbackByFeedbackId(String feedbackId) {
        if (feedbackId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        Feedback feedback = feedbackMapper.selectFeedbackByFeedbackId(feedbackId);
        return  FeedbackConvertUtils.feedbackInfoByFeedback(feedback);
    }

    @Override
    public int disabledFeedback(String feedbackId) {
        if (feedbackId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        Feedback updateFeedback = feedbackMapper.selectFeedbackByFeedbackId(feedbackId);
        if (updateFeedback == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        return feedbackMapper.disabledFeedback(feedbackId);
    }

    @Override
    public List<FeedbackInfo> selectAllFeedbackList() {
        List<Feedback> feedbackList = feedbackMapper.selectAllFeedbackList();
        List<FeedbackInfo> feedbackInfoList = FeedbackConvertUtils.feedbackInfoListByFeedbackList(feedbackList);
        return feedbackInfoList;
    }

    @Override
    public List<FeedbackInfo> selecFeedbackByFeedbackParamInfo(FeedbackParamInfo feedbackParamInfo) {
        FeedbackParamInfo paramInfo = new FeedbackParamInfo();
        paramInfo.setFeedbackId(feedbackParamInfo.getFeedbackId());
        paramInfo.setContactEmail(feedbackParamInfo.getContactEmail());
        paramInfo.setContactTel(feedbackParamInfo.getContactTel());
        paramInfo.setQuestionTypeId(feedbackParamInfo.getQuestionTypeId());
        if (feedbackParamInfo.getTitle() != null && !feedbackParamInfo.getTitle().isEmpty()) {
            paramInfo.setTitle("%" + feedbackParamInfo.getTitle() + "%");
        }
        if (feedbackParamInfo.getContent() != null && !feedbackParamInfo.getContent().isEmpty()) {
            paramInfo.setContent("%" + feedbackParamInfo.getContent() + "%");
        }
        List<Feedback> feedbackList = feedbackMapper.selecFeedbackByFeedbackParamInfo(paramInfo);
        List<FeedbackInfo> feedbackInfoList = FeedbackConvertUtils.feedbackInfoListByFeedbackList(feedbackList);
        return feedbackInfoList;
    }

    @Override
    public int deleteFeedbackByFeedbackId(String feedbackId) {
        if (feedbackId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return feedbackMapper.deleteFeedbackByFeedbackId(feedbackId);
    }
}
