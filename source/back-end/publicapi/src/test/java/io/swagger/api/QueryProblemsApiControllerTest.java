package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.BaseTest;
import io.swagger.model.Expression;
import io.swagger.model.QuerryInfo;
import net.minidev.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.spring.web.json.Json;

import java.util.UUID;

/**
 * 测试queryProblem接口
 */
@WebAppConfiguration
@ComponentScan(basePackages = "io.swagger.api")
public class QueryProblemsApiControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        super.before(); //载入基础测试数据
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryProblems() throws Exception {

        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        QuerryInfo querryInfo = new QuerryInfo();
        Expression expression = new Expression();
        expression.setOperator("==");
        expression.setArgument1("problemId");
        expression.setArgument2("1000002");
        querryInfo.setQuerry(expression);
        querryInfo.setPoolId(UUID.randomUUID());
        querryInfo.setToken(UUID.randomUUID());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(querryInfo);
        System.out.println(json);
//        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/queryProblems")
//                .contentType(MediaType.APPLICATION_JSON).content(json);
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/queryProblems")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"poolId\": \"4468c74d-759e-4d78-8c43-e1c5405f193b\",\n" +
                        "    \"token\": \"06e599f3-78db-4c71-b4fa-2b496beab1f6\",\n" +
                        "    \"querry\": {\n" +
                        "        \"operator\": \"==\",\n" +
                        "        \"argument1\":\"problemId\",\n" +
                        "        \"argument2\": \"1000002\"\n" +
                        "            \n" +
                        "    },\n" +
                        "    \"random\": false,\n" +
                        "    \"deep\": false,\n" +
                        "    \"ordering\": {\n" +
                        "        \"sorttype\": \"desc\",\n" +
                        "        \"field\": \"create_time\"\n" +
                        "    },\n" +
                        "    \"pagination\": {\n" +
                        "        \"page\": 1,\n" +
                        "        \"size\": 10,\n" +
                        "        \"total\": 0\n" +
                        "    }\n" +
                        "}");

        MvcResult mvcResult = this.mockMvc.perform(content).andDo(MockMvcResultHandlers.print()).andReturn();

    }
}