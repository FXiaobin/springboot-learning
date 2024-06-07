package com.example.springboot_learning.service;

import com.example.springboot_learning.model.questionType.QuestionTypeInfo;
import com.example.springboot_learning.model.questionType.QuestionTypeParamInfo;
import com.example.springboot_learning.pojo.QuestionType;

import java.util.List;

public interface QuestionTypeService {

    int addQuestionType(QuestionType questionType);

    int updateQuestionType(QuestionType questionType);

    QuestionTypeInfo selectQuestionTypeByQuestionTypeId(String questionTypeId);

    int disabledQuestionType(String questionTypeId);

    List<QuestionTypeInfo> selectAllQuestionTypeList();

    List<QuestionTypeInfo> selecQuestionTypeByQuestionTypeParamInfo(QuestionTypeParamInfo questionTypeParamInfo);

    int deleteQuestionTypeByQuestionTypeId(String questionTypeId);
    
}
