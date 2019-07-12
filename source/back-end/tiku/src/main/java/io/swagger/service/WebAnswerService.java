package io.swagger.service;

import io.swagger.pojo.dao.Answer;

public interface WebAnswerService {
    Answer add(Answer answer, Long createBy);
}
