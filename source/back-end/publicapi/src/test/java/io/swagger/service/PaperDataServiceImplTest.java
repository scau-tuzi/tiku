package io.swagger.service;

import io.swagger.BaseTest;
import io.swagger.Swagger2SpringBoot;
import io.swagger.pojo.PaperFullData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


public class PaperDataServiceImplTest extends BaseTest {

    @Autowired
    private PaperDataServiceImpl paperDataServiceImpl;

    @Test
    public void test() {
        ArrayList<Long> longs = new ArrayList<>();
        longs.add(100L);
        List<PaperFullData> fullDataByIds = paperDataServiceImpl.getFullDataByIds(longs, true);
        System.out.println("--------------------------------------");
        System.out.println(fullDataByIds);
        System.out.println("--------------------------------------");
    }

}