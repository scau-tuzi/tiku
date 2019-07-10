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
        ProblemTag problemTag = new ProblemTag();
        problemTag.setProblemId(1L);
        problemTag.setTagId(1L);

        System.out.println(problemTag);
    }
}
