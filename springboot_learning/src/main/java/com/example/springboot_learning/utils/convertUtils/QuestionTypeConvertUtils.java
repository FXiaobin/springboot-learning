package com.example.springboot_learning.utils.convertUtils;

import com.example.springboot_learning.model.questionType.QuestionTypeInfo;
import com.example.springboot_learning.pojo.QuestionType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class QuestionTypeConvertUtils {
    /**
     * 将QuestionType数据库获取的实体对象转成对应处理后接口要返回的实体数据
     * @param questionType
     * @return questionTypeInfo
     */
    public static QuestionTypeInfo questionTypeInfoByQuestionType(QuestionType questionType) {

        QuestionTypeInfo questionTypeInfo = new QuestionTypeInfo();
        BeanUtils.copyProperties(questionType, questionTypeInfo);

        return questionTypeInfo;
    }

    /**
     * 将QuestionType数据库获取的实体对象列表转成对应处理后接口要返回的实体数据列表
     * @param questionTypeList
     * @return questionTypeInfoList
     */
    public static List<QuestionTypeInfo> questionTypeInfoListByQuestionTypeList(List<QuestionType> questionTypeList) {

        List<QuestionTypeInfo> questionTypeInfoList = new ArrayList<>();
        for (QuestionType questionType : questionTypeList) {
            QuestionTypeInfo questionTypeInfo = questionTypeInfoByQuestionType(questionType);
            questionTypeInfoList.add(questionTypeInfo);
        }
        return questionTypeInfoList;
    }
}
