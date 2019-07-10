package cn.czfshine.tiku;

import cn.czfshine.tiku.pojo.dao.*;
import cn.czfshine.tiku.pojo.dao.repos.*;
import cn.czfshine.tiku.service.ProblemServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TikuApplicationTests {

    @Autowired

    @Test
    public void contextLoads() {
<<<<<<< HEAD
        ProblemTag problemTag = new ProblemTag();
        problemTag.setProblemId(1L);
        problemTag.setTagId(1L);

        System.out.println(problemTag);
=======
        // todo 自动生成测试数据，再测试，默认数据库啥都没
//        ProblemTag problemTag = new ProblemTag();
//        problemTag.setProblemId(1L);
//        problemTag.setTagId(1L);
//
//        Assert.assertNotNull(problemTagRepository.save(problemTag));
>>>>>>> e73f302573b5dfdab1e751e61ff083fb6451ebe3
    }
}
