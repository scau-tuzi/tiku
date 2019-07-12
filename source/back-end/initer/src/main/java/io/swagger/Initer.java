package io.swagger;

import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Initer {

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

    @Bean
    CommandLineRunner initDatabase(

    ) {

        return args -> {
            initData();
            if (problemRepository.count() == 0) {
                //如果数据库没有数据的话才装填数据进去

            } else {
                log.info("had data.");
            }
        };
    }

    private void initData() {
        log.info("Preloading data ...");

        long tid = 20000;
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

        tid = 20100;

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


        long baseid = 2000000;
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
                extData2.setValue(String.valueOf(i+j+1));
                extData2.setProblemId(curid);
                extDataRepository.save(extData2);
                exid++;
                ExtData extData3 = new ExtData();
                extData3.setId(exid);
                extData3.setKeyname("choice_C");
                extData3.setValue(String.valueOf(i+j+2));
                extData3.setProblemId(curid);
                extDataRepository.save(extData3);
                exid++;
                ExtData extData4 = new ExtData();
                extData4.setId(exid);
                extData4.setKeyname("choice_D");
                extData4.setValue(String.valueOf(i+j+3));
                extData4.setProblemId(curid);
                extDataRepository.save(extData4);
                exid++;

                ProblemTag problemTag = new ProblemTag();
                problemTag.setProblemId(curid);
                problemTag.setTagId(curid % 12 + 20000);
                problemTagRepository.save(problemTag);
                ProblemTag problemTag2 = new ProblemTag();
                problemTag2.setProblemId(curid);
                problemTag2.setTagId(((curid * 13) % 12) + 20100);
                problemTagRepository.save(problemTag2);
            }
        }
    }
}
