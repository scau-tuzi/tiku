package io.swagger;

import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Transient;

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

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperItemRepository paperItemRepository;
    @Autowired
    private PaperTagRepository paperTagRepository;

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
    @Transient
    private void initData() {
        log.info("Preloading data ...");

        long tid = 20000;
        Tag one = tagRepository.save(new Tag(tid++, "一年级", null));
        tid=one.getId();
        {

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
        }

        long baseid = 2000000;
        long curid = baseid;
        long exid = 1;
        long maxpr=0;
        for (int i = 1; i < 27; i++) {
            for (int j = 0; j < 31; j++) {
                String text = i + "+" + j + "=?";
                String ans = i + j + "";

                Answer answer = new Answer();
                answer.setId(curid);
                answer.setAnswerText("A");
                Answer an = answerRepository.save(answer);

                curid++;

                Problem problem = new Problem();
                problem.setProblemText(text);
                problem.setId(curid);
                problem.setAnswerId(an.getId());
                Problem pr = problemRepository.save(problem);
                maxpr=pr.getId();
                Status status = new Status();
                status.setProblemId(pr.getId());
                status.setVerifyStatus(0);
                statusRepository.save(status);
                {
                    ExtData extData1 = new ExtData();
                    extData1.setId(exid);
                    extData1.setKeyname("choice_A");
                    extData1.setValue(ans);
                    extData1.setProblemId(pr.getId());
                    extDataRepository.save(extData1);
                    exid++;
                    ExtData extData2 = new ExtData();
                    extData2.setId(exid);
                    extData2.setKeyname("choice_B");
                    extData2.setValue(String.valueOf(i + j + 1));
                    extData2.setProblemId(pr.getId());
                    extDataRepository.save(extData2);
                    exid++;
                    ExtData extData3 = new ExtData();
                    extData3.setId(exid);
                    extData3.setKeyname("choice_C");
                    extData3.setValue(String.valueOf(i + j + 2));
                    extData3.setProblemId(pr.getId());
                    extDataRepository.save(extData3);
                    exid++;
                    ExtData extData4 = new ExtData();
                    extData4.setId(exid);
                    extData4.setKeyname("choice_D");
                    extData4.setValue(String.valueOf(i + j + 3));
                    extData4.setProblemId(pr.getId());
                    extDataRepository.save(extData4);
                    exid++;
                }
                ProblemTag problemTag = new ProblemTag();
                problemTag.setProblemId(pr.getId());
                problemTag.setTagId(tid-(i%12));
                problemTagRepository.save(problemTag);
                ProblemTag problemTag2 = new ProblemTag();
                problemTag2.setProblemId(pr.getId());
                problemTag2.setTagId(tid-12-(j%12));
                problemTagRepository.save(problemTag2);
            }
        }

        long papid=23456789;
        for (int i = 0; i < 144; i++) {
            long a= i/12+tid-24;
            long b= i%12+tid-24;
            Paper paper = new Paper();
            paper.setId(papid+i);
            paper.setTitle("第"+i+"张试卷略略略");
            Paper pa = paperRepository.save(paper);
            for (int j = 0; j < 20; j++) {
                PaperItem paperItem = new PaperItem();
                paperItem.setPaperId(pa.getId());
                paperItem.setProblemId(maxpr-j*12-j);
                paperItem.setSerial(j);
                paperItemRepository.save(paperItem);
            }

            PaperTag paperTag = new PaperTag();
            paperTag.setPaperId(pa.getId());
            paperTag.setTagId(a);
            paperTagRepository.save(paperTag);

            PaperTag paperTag2 = new PaperTag();
            paperTag2.setPaperId(pa.getId());
            paperTag2.setTagId(b);
            paperTagRepository.save(paperTag2);

        }

    }
}
