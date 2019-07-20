package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import io.swagger.pojo.dao.repos.PaperRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class CollectionApiControllerTest extends QueryApiBaseTest {
    @Autowired
    private PaperRepository paperRepository;

    private String url="/collection";
    @Test
    public void successAdd() throws Exception {
        // 下面是一些调用样例，
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"poolId\": \"4468c74d-759e-4d78-8c43-e1c5405f193b\",\n" +
                        "  \"token\": \"06e599f3-78db-4c71-b4fa-2b496beab1f6\",\n" +
                        "  \"title\": \"试卷标题\",\n" +
                        "  \"problemIds\": [\n" +
                        "    \"806\",\n" +
                        "    \"804\",\n" +
                        "    \"800\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    \"一年级\",\n" +
                        "    \"语文\"\n" +
                        "  ]\n" +
                        "}");

        //预期结果
        //{
        //    "status": "ok",
        //    "results": "190"
        //}
        //
        this.mockMvc.perform(content)
                .andExpect(ok)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status").value("ok"))
                .andDo((r)->{
                    String contentAsString = r.getResponse().getContentAsString();
                    //todo 从数据库拿
                });
    }
    @Test
    public void fail() throws Exception {
        // 下面是一些调用样例，
        ResultMatcher badRequest = MockMvcResultMatchers.status().isBadRequest();
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"poolId\": \"4468c74d-759e-4d78-8c43-e1c5405f193b\",\n" +
                        "  \"token\": \"06e599f3-78db-4c71-b4fa-2b496beab1f6\",\n" +
                        "  \"title\": \"试卷标题\",\n" +
                        "  \"problemIds\":\"806\", \n" +  //error
                        "  \"tags\": [\n" +
                        "    \"一年级\",\n" +
                        "    \"语文\"\n" +
                        "  ]\n" +
                        "}");

        //预期结果
        // badRequest
        this.mockMvc.perform(content)
                .andExpect(badRequest)
                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.status").value("ok"))
//                .andDo((r)->{
//                    String contentAsString = r.getResponse().getContentAsString();
//                    //todo 从数据库拿
//                })
        ;
    }

}