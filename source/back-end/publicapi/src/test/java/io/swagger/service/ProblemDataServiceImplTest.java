package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.Swagger2SpringBoot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class ProblemDataServiceImplTest {


    @Autowired
    private ProblemDataServiceImpl problemDataServiceImpl;

    @Test
    public void test() {
        ArrayList<Long> longs = new ArrayList<>();
        longs.add(1000002L);
        List<ProblemFullData> fullDataByIds = problemDataServiceImpl.getFullDataByIds(longs);
        System.out.println(fullDataByIds);
    }
}