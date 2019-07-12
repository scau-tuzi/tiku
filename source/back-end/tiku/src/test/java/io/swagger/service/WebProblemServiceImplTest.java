package io.swagger.service;

import io.swagger.TikuApplication;
import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TikuApplication.class)
@ContextConfiguration(classes = TikuApplication.class)
@WebAppConfiguration
public class WebProblemServiceImplTest {

    @Autowired
    private WebProblemService webProblemService;

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
        extData1.setKeyname("A");
        extData1.setValue("eva");
        extData1.setProblemId(1000002L);
        extDataRepository.save(extData1);

        ExtData extData2 = new ExtData();
        extData2.setId(2L);
        extData2.setKeyname("B");
        extData2.setValue("bobo");
        extData2.setProblemId(1000002L);
        extDataRepository.save(extData2);

        Tag tag = new Tag();
        tag.setParentId(2L);
        tag.setValue("标签1");
        tag.setId(1L);
        tagRepository.save(tag);

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setValue("标签2");
        tagRepository.save(tag2);

        ProblemTag problemTag = new ProblemTag();
        problemTag.setProblemId(1000002L);
        problemTag.setTagId(1L);
        problemTagRepository.save(problemTag);

        Problem problem = new Problem();
        problem.setProblemText("123");
        problem.setId(1000002L);
        problem.setAnswerId(100000L);
        problemRepository.save(problem);

        Problem problem1 = new Problem();
        problem1.setProblemText("888");
        problem1.setId(1000003L);
        problemRepository.save(problem1);
    }


    @Test
    public void test() {
        List<ProblemFullData> problemFullDataList = webProblemService.getAll(0, 2);
        System.out.println("--------------------------------------");
        System.out.println(problemFullDataList);
        System.out.println("--------------------------------------");
    }
}