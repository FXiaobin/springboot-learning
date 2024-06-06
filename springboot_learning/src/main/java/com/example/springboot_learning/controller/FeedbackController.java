package com.example.springboot_learning.controller;


import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.model.feedback.FeedbackInfo;
import com.example.springboot_learning.model.feedback.FeedbackParamInfo;
import com.example.springboot_learning.pojo.Feedback;
import com.example.springboot_learning.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@Tag(name = "意见反馈feedback", description = "意见反馈接口")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    /*
     *   总结：
     *   1. 请求参数是一个对象，所以就算只有一个参数也要放到一个对象中，这里就需要创建一个参数实体类了；
     *   2. 请求参数需要添加@RequestBody注解，否则接口接收不到参数传递过来的数据
     *   3. 最好使用RequestMapping来设置value和method，否则可能有问题
     *
     * */

    /**
     * 新增意见反馈
     * @param feedback
     * @return
     */
    @Operation(summary = "意见反馈-新增", description = "用于新增一条意见反馈信息")
    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    public ResponseInfo addFeedback(@RequestBody Feedback feedback) {
        int result = feedbackService.addFeedback(feedback);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, feedback);

        return responseInfo;
    }

    @Operation(summary = "意见反馈-修改意见反馈信息")
    @RequestMapping(value = "/updateFeedback", method = RequestMethod.PUT)
    public ResponseInfo updateFeedback(@RequestBody Feedback feedback) {
        int result = feedbackService.updateFeedback(feedback);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }

    @Operation(summary = "意见反馈-禁用某个意见反馈")
    @RequestMapping(value = "/disabledFeedback", method = RequestMethod.PUT)
    public ResponseInfo disabledFeedback(@RequestBody FeedbackParamInfo feedbackParamInfo) {
        int result = feedbackService.disabledFeedback(feedbackParamInfo.getFeedbackId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }


    @Operation(summary = "意见反馈-根据feedbackId查询意见反馈信息")
    @RequestMapping(value = "/selectFeedbackByFeedbackId", method = RequestMethod.POST)
    public ResponseInfo selectFeedbackByFeedbackId(@RequestBody FeedbackParamInfo feedbackParamInfo) {
        FeedbackInfo feedbackInfo = feedbackService.selectFeedbackByFeedbackId(feedbackParamInfo.getFeedbackId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(feedbackInfo);

        return responseInfo;
    }

    @Operation(summary = "意见反馈-查询所有意见反馈")
    @RequestMapping(value = "/selectAllFeedbackList", method = RequestMethod.POST)
    public ResponseInfo selectAllFeedbackList() {
        List<FeedbackInfo> feedbackInfoList = feedbackService.selectAllFeedbackList();
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(feedbackInfoList);

        return responseInfo;
    }

    @Operation(summary = "意见反馈-按条件查询意见反馈信息")
    @RequestMapping(value = "/selecFeedbackByFeedbackParamInfo", method = RequestMethod.POST)
    public ResponseInfo selecFeedbackByFeedbackParamInfo(@RequestBody FeedbackParamInfo feedbackParamInfo) {
        List<FeedbackInfo> feedbackInfoList = feedbackService.selecFeedbackByFeedbackParamInfo(feedbackParamInfo);
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(feedbackInfoList);

        return responseInfo;
    }

    @Operation(summary = "意见反馈-意见反馈永久删除 慎用！！！")
    @RequestMapping(value = "/deleteFeedbackByFeedbackId", method = RequestMethod.DELETE)
    public ResponseInfo deleteFeedbackByFeedbackId(@RequestBody FeedbackParamInfo feedbackParamInfo ) {
        int result = feedbackService.deleteFeedbackByFeedbackId(feedbackParamInfo.getFeedbackId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }


}

