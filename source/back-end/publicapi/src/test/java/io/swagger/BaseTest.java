package io.swagger;


import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础测试类，初始化数据用
 */
@RunWith(SpringJUnit4ClassRunner.class)

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

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperTagRepository paperTagRepository;

    @Autowired
    private PaperItemRepository paperItemRepository;

    protected String baseurl = "/tiku/api/v1";

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

        PaperTag paperTag1 = new PaperTag();
        paperTag1.setPaperId(100L);
        paperTag1.setTagId(1L);
        paperTagRepository.save(paperTag1);

        PaperTag paperTag2 = new PaperTag();
        paperTag2.setPaperId(100L);
        paperTag2.setTagId(2L);
        paperTagRepository.save(paperTag2);

        PaperItem paperItem1 = new PaperItem();
        paperItem1.setPaperId(100L);
        paperItem1.setProblemId(1000002L);
        paperItem1.setSerial(2);
        paperItemRepository.save(paperItem1);


        PaperItem paperItem2 = new PaperItem();
        paperItem2.setPaperId(100L);
        paperItem2.setProblemId(1000003L);
        paperItem2.setSerial(1);
        paperItemRepository.save(paperItem2);

        Paper paper = new Paper();
        paper.setId(100L);
        paper.setTitle("I am a paper");
        paperRepository.save(paper);

    }

}
