package io.swagger;


import io.swagger.Swagger2SpringBoot;
import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * 基础测试类，初始化数据用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
@ContextConfiguration(classes = Swagger2SpringBoot.class)
public class BaseTest {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ExtDataRepository extDataRepository;

    @Autowired
    private ProblemTagRepository problemTagRepository;

    @Autowired
    private TagRepository tagRepository;

    protected String baseurl="/tiku/api/v1";

    @Before
    public void before() {

        Answer answer = new Answer();
        answer.setId(100000L);
        answer.setAnswerText("12313545646");
        answerRepository.save(answer);

        Status status = new Status();
        status.setProblemId(1000002L);
        status.setVerifyStatus(0);
        statusRepository.save(status);

        ExtData extData1 = new ExtData();
        extData1.setId(1L);
        extData1.setKey("A");
        extData1.setValue("eva");
        extData1.setProblemId(1000002L);
        extDataRepository.save(extData1);

        ExtData extData2 = new ExtData();
        extData2.setId(2L);
        extData2.setKey("B");
        extData2.setValue("bobo");
        extData2.setProblemId(1000002L);
        extDataRepository.save(extData2);

        Tag tag = new Tag();
        tag.setParentId(1L);
        tag.setValue("标签1");
        tag.setId(1L);
        tagRepository.save(tag);

        ProblemTag problemTag = new ProblemTag();
        problemTag.setProblemId(1000002L);
        problemTag.setTagId(1L);
        problemTagRepository.save(problemTag);

        Problem problem = new Problem();
        problem.setProblemText("123");
        problem.setId(1000002L);
        problem.setAnswerId(100000L);
        problemRepository.save(problem);

    }

}
