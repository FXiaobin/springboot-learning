package com.example.springboot_learning.mapper;

import com.example.springboot_learning.model.questionType.QuestionTypeParamInfo;
import com.example.springboot_learning.pojo.QuestionType;

import java.util.List;

public interface QuestionTypeMapper {

    int addQuestionType(QuestionType questionType);

    int updateQuestionType(QuestionType questionType);

    int disabledQuestionType(String questionTypeId);

    List<QuestionType> selectAllQuestionTypeList();

    QuestionType selectQuestionTypeByQuestionTypeId(String questionTypeId);

    List<QuestionType> selecQuestionTypeByQuestionTypeParamInfo(QuestionTypeParamInfo questionTypeParamInfo);

    int deleteQuestionTypeByQuestionTypeId(String questionTypeId);
}
