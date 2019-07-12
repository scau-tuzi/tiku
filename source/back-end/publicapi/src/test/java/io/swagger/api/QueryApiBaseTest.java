package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ComponentScan(basePackages = "io.swagger.api")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Swagger2SpringBoot.class)
public class QueryApiBaseTest {
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
    private WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
        // 测试数据，仅本接口使用，勿改，不然下面的测试都得变
        //todo 提取到别处

        long tid = 10000;
        tagRepository.save(new Tag(tid++, "一年级", null));
        tagRepository.save(new Tag(tid++, "二年级", null));
        tagRepository.save(new Tag(tid++, "三年级", null));
        tagRepository.save(new Tag(tid++, "四年级", null));
        tagRepository.save(new Tag(tid++, "五年级", null));
        tagRepository.save(new Tag(tid++, "六年级", null));
        tagRepository.save(new Tag(tid++, "初一", null));
        tagRepository.save(new Tag(tid++, "初二", null));
        tagRepository.save(new Tag(tid++, "初三", null));
        tagRepository.save(new Tag(tid++, "高一", null));
        tagRepository.save(new Tag(tid++, "高二", null));
        tagRepository.save(new Tag(tid++, "高三", null));

        tid = 10100;

        tagRepository.save(new Tag(tid++, "语文", null));
        tagRepository.save(new Tag(tid++, "数学", null));
        tagRepository.save(new Tag(tid++, "英语", null));
        tagRepository.save(new Tag(tid++, "历史", null));
        tagRepository.save(new Tag(tid++, "地理", null));
        tagRepository.save(new Tag(tid++, "化学", null));
        tagRepository.save(new Tag(tid++, "生物", null));
        tagRepository.save(new Tag(tid++, "物理", null));
        tagRepository.save(new Tag(tid++, "美术", null));
        tagRepository.save(new Tag(tid++, "计算机", null));
        tagRepository.save(new Tag(tid++, "编程", null));
        tagRepository.save(new Tag(tid++, "打码", null));


        long baseid = 1000000;
        long curid = baseid;
        long exid = 1;
        for (int i = 1; i < 27; i++) {
            for (int j = 0; j < 31; j++) {
                String text = i + "+" + j + "=?";
                String ans = i + j + "";

                Answer answer = new Answer();
                answer.setId(curid);
                answer.setAnswerText(ans);
                answerRepository.save(answer);

                curid++;

                Problem problem = new Problem();
                problem.setProblemText(text);
                problem.setId(curid);
                problem.setAnswerId(curid - 1);
                problemRepository.save(problem);

                Status status = new Status();
                status.setProblemId(curid);
                status.setVerifyStatus(0);
                statusRepository.save(status);

                ExtData extData1 = new ExtData();
                extData1.setId(exid);
                extData1.setKeyname("choice_A");
                extData1.setValue(ans);
                extData1.setProblemId(curid);
                extDataRepository.save(extData1);
                exid++;
                ExtData extData2 = new ExtData();
                extData2.setId(exid);
                extData2.setKeyname("choice_B");
                extData2.setValue(ans);
                extData2.setProblemId(curid);
                extDataRepository.save(extData2);
                exid++;
                ExtData extData3 = new ExtData();
                extData3.setId(exid);
                extData3.setKeyname("choice_C");
                extData3.setValue(ans);
                extData3.setProblemId(curid);
                extDataRepository.save(extData3);
                exid++;
                ExtData extData4 = new ExtData();
                extData4.setId(exid);
                extData4.setKeyname("choice_D");
                extData4.setValue(ans);
                extData4.setProblemId(curid);
                extDataRepository.save(extData4);
                exid++;

                ProblemTag problemTag = new ProblemTag();
                problemTag.setProblemId(curid);
                problemTag.setTagId(curid % 12 + 10000);
                problemTagRepository.save(problemTag);
                ProblemTag problemTag2 = new ProblemTag();
                problemTag2.setProblemId(curid);
                problemTag2.setTagId(((curid * 13) % 12) + 10100);
                problemTagRepository.save(problemTag2);
            }
        }
    }
    @Test
    public void test(){

    }
}
