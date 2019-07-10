package io.swagger.service;


import io.swagger.Swagger2SpringBoot;
import io.swagger.pojo.dao.Answer;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dao.repos.AnswerRepository;
import io.swagger.pojo.dao.repos.ProblemRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * 基础测试类，初始化数据用
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class BaseTest {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Before
    public void before(){

        Answer answer = new Answer();
        answer.setId(100000L);
        answer.setAnswerText("12313545646");
        answerRepository.save(answer);

        Problem problem = new Problem();
        problem.setProblemText("123");
        problem.setId(1000002L);
        problem.setAnswerId(100000L);
        problemRepository.save(problem);

    }

}
