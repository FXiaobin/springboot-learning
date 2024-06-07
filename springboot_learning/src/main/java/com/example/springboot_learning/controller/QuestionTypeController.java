package com.example.springboot_learning.controller;


import com.example.springboot_learning.model.questionType.QuestionTypeInfo;
import com.example.springboot_learning.model.questionType.QuestionTypeParamInfo;
import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.pojo.QuestionType;
import com.example.springboot_learning.service.QuestionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questionType")
@Tag(name = "意见反馈问题类型questionType", description = "意见反馈问题类型接口")
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;


    /*
     *   总结：
     *   1. 请求参数是一个对象，所以就算只有一个参数也要放到一个对象中，这里就需要创建一个参数实体类了；
     *   2. 请求参数需要添加@RequestBody注解，否则接口接收不到参数传递过来的数据
     *   3. 最好使用RequestMapping来设置value和method，否则可能有问题
     *
     * */

    /**
     * 新增意见反馈问题类型
     * @param questionType
     * @return
     */
    @Operation(summary = "意见反馈问题类型-新增", description = "用于新增一条意见反馈问题类型信息")
    @RequestMapping(value = "/addQuestionType", method = RequestMethod.POST)
    public ResponseInfo addQuestionType(@RequestBody QuestionType questionType) {
        int result = questionTypeService.addQuestionType(questionType);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, questionType);

        return responseInfo;
    }

    @Operation(summary = "意见反馈问题类型-修改意见反馈问题类型信息")
    @RequestMapping(value = "/updateQuestionType", method = RequestMethod.PUT)
    public ResponseInfo updateQuestionType(@RequestBody QuestionType questionType) {
        int result = questionTypeService.updateQuestionType(questionType);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }

    @Operation(summary = "意见反馈问题类型-禁用某个意见反馈问题类型")
    @RequestMapping(value = "/disabledQuestionType", method = RequestMethod.PUT)
    public ResponseInfo disabledQuestionType(@RequestBody QuestionTypeParamInfo questionTypeParamInfo) {
        int result = questionTypeService.disabledQuestionType(questionTypeParamInfo.getQuestionTypeId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }


    @Operation(summary = "意见反馈问题类型-根据questionTypeId查询意见反馈问题类型信息")
    @RequestMapping(value = "/selectQuestionTypeByQuestionTypeId", method = RequestMethod.POST)
    public ResponseInfo selectQuestionTypeByQuestionTypeId(@RequestBody QuestionTypeParamInfo questionTypeParamInfo) {
        QuestionTypeInfo questionTypeInfo = questionTypeService.selectQuestionTypeByQuestionTypeId(questionTypeParamInfo.getQuestionTypeId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(questionTypeInfo);

        return responseInfo;
    }

    @Operation(summary = "意见反馈问题类型-查询所有意见反馈问题类型")
    @RequestMapping(value = "/selectAllQuestionTypeList", method = RequestMethod.POST)
    public ResponseInfo selectAllQuestionTypeList() {
        List<QuestionTypeInfo> questionTypeInfoList = questionTypeService.selectAllQuestionTypeList();
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(questionTypeInfoList);

        return responseInfo;
    }

    @Operation(summary = "意见反馈问题类型-按条件查询意见反馈问题类型信息")
    @RequestMapping(value = "/selecQuestionTypeByQuestionTypeParamInfo", method = RequestMethod.POST)
    public ResponseInfo selecQuestionTypeByQuestionTypeParamInfo(@RequestBody QuestionTypeParamInfo questionTypeParamInfo) {
        List<QuestionTypeInfo> questionTypeInfoList = questionTypeService.selecQuestionTypeByQuestionTypeParamInfo(questionTypeParamInfo);
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(questionTypeInfoList);

        return responseInfo;
    }

    @Operation(summary = "意见反馈问题类型-意见反馈问题类型永久删除 慎用！！！")
    @RequestMapping(value = "/deleteQuestionTypeByQuestionTypeId", method = RequestMethod.DELETE)
    public ResponseInfo deleteQuestionTypeByQuestionTypeId(@RequestBody QuestionTypeParamInfo questionTypeParamInfo ) {
        int result = questionTypeService.deleteQuestionTypeByQuestionTypeId(questionTypeParamInfo.getQuestionTypeId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }


}
