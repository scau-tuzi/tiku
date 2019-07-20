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
    @Autowired
    private TikuUserRepository tikuUserRepository;
    @Autowired
    private UserProblemStatusRepository userProblemStatusRepository;
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

        long tid =0;
        Tag one = tagRepository.save(new Tag(null, "一年级", null));
        tid=one.getId();
        tid++;
        log.debug("tid:{}",tid);
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
        tid-=24;

        long baseid = 2000000;
        long curid = baseid;
        long exid = 1;
        long maxpr=0;
        int kk=0;
        // TAG1 12 * TAG2 12 * PAPAER 10 * PROBLEM 20 =48000 PROBLEMS
        for (int i = (int) tid; i < tid+6;i++) {
            log.info(String.valueOf(i));
            for (int j = (int) (tid + 12); j < tid + 18; j++) {
                log.info(String.valueOf(j));
                for (long paperNo = 0; paperNo < 5; paperNo++) {
                    // 生成试卷
                    long papid=23456789;
                    kk++;
                    Paper paper = new Paper();
                    paper.setId(papid+kk);
                    paper.setTitle("第"+kk+"张试卷略略略");
                    Paper pa = paperRepository.save(paper);

                    PaperTag paperTag = new PaperTag();
                    paperTag.setPaperId(pa.getId());
                    paperTag.setTagId((long) i);
                    paperTagRepository.save(paperTag);

                    PaperTag paperTag2 = new PaperTag();
                    paperTag2.setPaperId(pa.getId());
                    paperTag2.setTagId((long) j);
                    paperTagRepository.save(paperTag2);

                    for (int proNo = 0; proNo < 10; proNo++) {

                        String text = (i*paperNo+proNo) + "+" + (j*proNo+paperNo) + "=?";
                        int ans = (int) (i*paperNo+proNo + j*proNo+paperNo);


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

                        maxpr = pr.getId();
                        Status status = new Status();
                        status.setProblemId(pr.getId());
                        status.setVerifyStatus(1);
                        statusRepository.save(status);

                        {
                            ExtData extData1 = new ExtData();
                            extData1.setId(exid);
                            extData1.setKeyname("choice_A");
                            extData1.setValue(String.valueOf(ans));
                            extData1.setProblemId(pr.getId());
                            extDataRepository.save(extData1);

                            exid++;
                            ExtData extData2 = new ExtData();
                            extData2.setId(exid);
                            extData2.setKeyname("choice_B");
                            extData2.setValue(String.valueOf(ans+i + j + 1));
                            extData2.setProblemId(pr.getId());
                            extDataRepository.save(extData2);

                            exid++;
                            ExtData extData3 = new ExtData();
                            extData3.setId(exid);
                            extData3.setKeyname("choice_C");
                            extData3.setValue(String.valueOf(ans+i + j + 2));
                            extData3.setProblemId(pr.getId());
                            extDataRepository.save(extData3);

                            exid++;
                            ExtData extData4 = new ExtData();
                            extData4.setId(exid);
                            extData4.setKeyname("choice_D");
                            extData4.setValue(String.valueOf(ans+i + j + 3));
                            extData4.setProblemId(pr.getId());
                            extDataRepository.save(extData4);

                            exid++;
                        }
                        ProblemTag problemTag = new ProblemTag();
                        problemTag.setProblemId(pr.getId());
                        problemTag.setTagId((long) i);
                        problemTagRepository.save(problemTag);
                        ProblemTag problemTag2 = new ProblemTag();
                        problemTag2.setProblemId(pr.getId());
                        problemTag2.setTagId((long) j);
                        problemTagRepository.save(problemTag2);

                        PaperItem paperItem = new PaperItem();
                        paperItem.setPaperId(pa.getId());
                        paperItem.setProblemId(pr.getId());
                        paperItem.setSerial(proNo);
                        paperItemRepository.save(paperItem);

                    }
                }
            }
        }

        //用户的
        TikuUser tikuUser = new TikuUser();
        tikuUser.setSalt("c80f4f74bb1b897252c5a8ff961ef504")
                .setPasswordSaltMd5("17279512649567851aa2a174741e539c")
                .setUsername("czfshine")
                .setUserUuid("1db837b9-c6b2-432d-a1d2-1c3cff8db473");
        TikuUser save = tikuUserRepository.save(tikuUser);

        userProblemStatusRepository.save(new UserProblemStatus()
                .setProblemId(curid-10)
                .setUserUuid(save.getUserUuid())
                .setStatus("错题"));

        userProblemStatusRepository.save(new UserProblemStatus()
                .setProblemId(curid-12)
                .setUserUuid(save.getUserUuid())
                .setStatus("错题"));
        userProblemStatusRepository.save(new UserProblemStatus()
                .setProblemId(curid-11)
                .setUserUuid(save.getUserUuid())
                .setStatus("错题"));
        userProblemStatusRepository.save(new UserProblemStatus()
                .setProblemId(curid-13)
                .setUserUuid(save.getUserUuid())
                .setStatus("错题"));

    }
}
