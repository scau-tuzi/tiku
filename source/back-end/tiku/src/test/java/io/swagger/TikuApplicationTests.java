package io.swagger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TikuApplication.class)
public class TikuApplicationTests {

    @Autowired

    @Test
    public void contextLoads() {
        // todo 自动生成测试数据，再测试，默认数据库啥都没
//        ProblemTagDto problemTag = new ProblemTagDto();
//        problemTag.setProblemId(1L);
//        problemTag.setTagId(1L);
//
//        Assert.assertNotNull(problemTagRepository.save(problemTag));
    }

}
