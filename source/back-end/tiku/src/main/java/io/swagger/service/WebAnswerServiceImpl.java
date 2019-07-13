package io.swagger.service;

import io.swagger.pojo.dao.Answer;
import io.swagger.pojo.dao.repos.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAnswerServiceImpl extends BasicService<Answer> implements WebAnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer add(Answer answer, Long createBy) {
        super.beforeAdd(answer, createBy);
        return answerRepository.save(answer);
    }

    @Override
    public int deleteById(Long id) {
        return answerRepository.updateIsDelById(id, Boolean.TRUE);
    }

    @Override
    public Answer update(Answer answer, Long updateBy) {
        Answer dbAnswer = answerRepository.findById(answer.getId()).get();
        dbAnswer.setAnswerText(answer.getAnswerText());
        dbAnswer.setAnswerImages(answer.getAnswerImages());
        super.beforeUpdate(dbAnswer, updateBy);
        return answerRepository.save(dbAnswer);
    }

}
