package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.FeedbackMapper;
import com.example.springboot_learning.model.feedback.FeedbackInfo;
import com.example.springboot_learning.model.feedback.FeedbackParamInfo;
import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.model.responseInfo.PageResponseInfo;
import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.pojo.Feedback;
import com.example.springboot_learning.service.FeedbackService;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import com.example.springboot_learning.utils.convertUtils.FeedbackConvertUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

        if (feedback.getContactEmail() == null || feedback.getTitle() == null || feedback.getQuestionTypeId() == null) {
            throw new BaseErrorException(BaseErrorEnum.PARAMETER_ERROR);
        }

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

    /**
     * 根据分页参数查询所有反馈信息列表。
     *
     * @param pageParamsInfo 分页参数信息，包含当前页码和每页大小。
     * @return 包含反馈信息列表和分页信息的响应对象。
     */
    @Override
    public ResponseInfo selectAllFeedbackList(PageParamsInfo pageParamsInfo) {

        // ************* PageHelper插件 分页处理 *************

        // 分页参数可传么不传  不传的时候为查询所有数据
        if (pageParamsInfo.getPageNum() != null && pageParamsInfo.getPageSize() != null) {
            // 初始化分页插件（这里初始化分页后，紧跟着的一条mybatis查询方法就会被自动分页处理，注意：是紧跟着的后面的第一个mybatis查询会被分页）
            PageHelper.startPage(pageParamsInfo.getPageNum(), pageParamsInfo.getPageSize());
        }

        // 查询分页对应的反馈信息列表（插件会自动处理分页查询，这里甚至不用传分页参数pageParamsInfo）

        List<Feedback> feedbackList = feedbackMapper.selectAllFeedbackList(pageParamsInfo);

        // 构建分页信息
        PageInfo<Feedback> pageInfo = new PageInfo<>(feedbackList);

        // 将反馈信息转换为反馈详情列表
        List<FeedbackInfo> feedbackInfoList = FeedbackConvertUtils.feedbackInfoListByFeedbackList(feedbackList);

        // 构建分页响应信息
        PageResponseInfo pageResponseInfo = new PageResponseInfo(pageInfo);

        // 构建并返回成功响应信息，包含反馈详情列表和分页信息
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(feedbackInfoList, pageResponseInfo);

        return responseInfo;
    }

    @Override
    public ResponseInfo selecFeedbackByFeedbackParamInfo(FeedbackParamInfo feedbackParamInfo) {
        FeedbackParamInfo paramInfo = new FeedbackParamInfo();
        paramInfo.setFeedbackId(feedbackParamInfo.getFeedbackId());
        paramInfo.setContactEmail(feedbackParamInfo.getContactEmail());
        paramInfo.setContactTel(feedbackParamInfo.getContactTel());
        paramInfo.setQuestionTypeId(feedbackParamInfo.getQuestionTypeId());
        paramInfo.setPageNum(feedbackParamInfo.getPageNum());
        paramInfo.setPageSize(feedbackParamInfo.getPageSize());

        if (feedbackParamInfo.getTitle() != null && !feedbackParamInfo.getTitle().isEmpty()) {
            paramInfo.setTitle("%" + feedbackParamInfo.getTitle() + "%");
        }
        if (feedbackParamInfo.getContent() != null && !feedbackParamInfo.getContent().isEmpty()) {
            paramInfo.setContent("%" + feedbackParamInfo.getContent() + "%");
        }

        // 分页参数可传么不传  不传的时候为查询所有数据
        if (paramInfo.getPageNum() != null && paramInfo.getPageSize() != null) {
            // 初始化分页插件（这里初始化分页后，紧跟着的一条mybatis查询方法就会被自动分页处理，注意：是紧跟着的后面的第一个mybatis查询会被分页）
            PageHelper.startPage(paramInfo.getPageNum(), paramInfo.getPageSize());
        }

        List<Feedback> feedbackList = feedbackMapper.selecFeedbackByFeedbackParamInfo(paramInfo);
        // 构建分页信息
        PageInfo<Feedback> pageInfo = new PageInfo<>(feedbackList);

        PageResponseInfo pageResponseInfo = new PageResponseInfo(pageInfo);
        List<FeedbackInfo> feedbackInfoList = FeedbackConvertUtils.feedbackInfoListByFeedbackList(feedbackList);

        // 构建并返回成功响应信息，包含反馈详情列表和分页信息
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(feedbackInfoList, pageResponseInfo);


        return responseInfo;
    }

    @Override
    public int deleteFeedbackByFeedbackId(String feedbackId) {
        if (feedbackId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return feedbackMapper.deleteFeedbackByFeedbackId(feedbackId);
    }
}
