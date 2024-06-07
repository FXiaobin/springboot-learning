package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.QuestionTypeMapper;
import com.example.springboot_learning.model.questionType.QuestionTypeInfo;
import com.example.springboot_learning.model.questionType.QuestionTypeParamInfo;
import com.example.springboot_learning.pojo.QuestionType;
import com.example.springboot_learning.service.QuestionTypeService;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import com.example.springboot_learning.utils.convertUtils.QuestionTypeConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

    @Autowired
    private QuestionTypeMapper questionTypeMapper;

    @Override
    public int addQuestionType(QuestionType questionType) {
        questionType.setQuestionTypeId(CommonUtils.getUUID());
        questionType.setSort(0);
        questionType.setStatus(0);
        questionType.setCreateTime(new Date());
        questionType.setUpdateTime(new Date());

        if (questionType.getTitle() == null) {
            throw new BaseErrorException(BaseErrorEnum.PARAMETER_ERROR);
        }

        return questionTypeMapper.addQuestionType(questionType);
    }

    @Override
    public int updateQuestionType(QuestionType questionType) {
        if (questionType.getQuestionTypeId() == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        QuestionType updateQuestionType = questionTypeMapper.selectQuestionTypeByQuestionTypeId(questionType.getQuestionTypeId());
        if (updateQuestionType == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        questionType.setUpdateTime(new Date());
        return questionTypeMapper.updateQuestionType(questionType);
    }

    @Override
    public QuestionTypeInfo selectQuestionTypeByQuestionTypeId(String questionTypeId) {
        if (questionTypeId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        QuestionType questionType = questionTypeMapper.selectQuestionTypeByQuestionTypeId(questionTypeId);
        return  QuestionTypeConvertUtils.questionTypeInfoByQuestionType(questionType);
    }

    @Override
    public int disabledQuestionType(String questionTypeId) {
        if (questionTypeId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        QuestionType updateQuestionType = questionTypeMapper.selectQuestionTypeByQuestionTypeId(questionTypeId);
        if (updateQuestionType == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        return questionTypeMapper.disabledQuestionType(questionTypeId);
    }

    @Override
    public List<QuestionTypeInfo> selectAllQuestionTypeList() {
        List<QuestionType> questionTypeList = questionTypeMapper.selectAllQuestionTypeList();
        List<QuestionTypeInfo> questionTypeInfoList = QuestionTypeConvertUtils.questionTypeInfoListByQuestionTypeList(questionTypeList);
        return questionTypeInfoList;
    }

    @Override
    public List<QuestionTypeInfo> selecQuestionTypeByQuestionTypeParamInfo(QuestionTypeParamInfo questionTypeParamInfo) {
        QuestionTypeParamInfo paramInfo = new QuestionTypeParamInfo();
        paramInfo.setQuestionTypeId(questionTypeParamInfo.getQuestionTypeId());
        paramInfo.setCreateUser(questionTypeParamInfo.getCreateUser());
        paramInfo.setStatus(questionTypeParamInfo.getStatus());
        paramInfo.setDetail(questionTypeParamInfo.getDetail());
        paramInfo.setTitle(questionTypeParamInfo.getTitle());

        if (questionTypeParamInfo.getTitle() != null && !questionTypeParamInfo.getTitle().isEmpty()) {
            paramInfo.setTitle("%" + questionTypeParamInfo.getTitle() + "%");
        }
        if (questionTypeParamInfo.getDetail() != null && !questionTypeParamInfo.getDetail().isEmpty()) {
            paramInfo.setDetail("%" + questionTypeParamInfo.getDetail() + "%");
        }
        List<QuestionType> questionTypeList = questionTypeMapper.selecQuestionTypeByQuestionTypeParamInfo(paramInfo);
        List<QuestionTypeInfo> questionTypeInfoList = QuestionTypeConvertUtils.questionTypeInfoListByQuestionTypeList(questionTypeList);
        return questionTypeInfoList;
    }

    @Override
    public int deleteQuestionTypeByQuestionTypeId(String questionTypeId) {
        if (questionTypeId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return questionTypeMapper.deleteQuestionTypeByQuestionTypeId(questionTypeId);
    }

}
