package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * 测试queryProblem接口
 */
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class QueryProblemsApiControllerTest extends QueryApiBaseTest {

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryProblems() throws Exception {
        // 下面是一些调用样例，
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/queryProblems")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"poolId\": \"4468c74d-759e-4d78-8c43-e1c5405f193b\",\n" +
                        "    \"token\": \"06e599f3-78db-4c71-b4fa-2b496beab1f6\",\n" +
                        "    \"querry\": {\n" +
                        "        \"operator\": \"==\",\n" +
                        "        \"argument1\":\"problemId\",\n" +
                        "        \"argument2\": \"1000222\"\n" +
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
        //预期结果{"status":"ok",
        // "results":[{"answer":"12",
        // "choice_B":"12",
        // "choice_C":"12",
        // "choice_D":"12",
        // "text":"8+4=?",
        // "problemId":1000222,
        // "tags":["高二","编程"],
        // "choice_A":"12",
        // "status":0}]}
        //
        this.mockMvc.perform(content)
                .andExpect(ok)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status").value("ok"));

        MockHttpServletRequestBuilder content2 = MockMvcRequestBuilders.post("/queryProblems")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "  \"poolId\": \"4468c74d-759e-4d78-8c43-e1c5405f193b\",\n" +
                        "  \"token\": \"06e599f3-78db-4c71-b4fa-2b496beab1f6\",\n" +
                        "  \"querry\": {\n" +
                        "    \"operator\": \"or\",\n" +
                        "    \"argument1\":{\n" +
                        "      \"operator\": \"==\",\n" +
                        "      \"argument1\":\"problemId\",\n" +
                        "      \"argument2\": \"1000233\"\n" +
                        "    },\n" +
                        "    \"argument2\": {\n" +
                        "      \"operator\": \"contains\",\n" +
                        "      \"argument1\":\"tags\",\n" +
                        "      \"argument2\": [\"高二\",\"编程\"]\n" +
                        "    }\n" +
                        "\n" +
                        "  },\n" +
                        "  \"random\": false,\n" +
                        "  \"deep\": false,\n" +
                        "  \"ordering\": {\n" +
                        "    \"sorttype\": \"desc\",\n" +
                        "    \"field\": \"create_time\"\n" +
                        "  },\n" +
                        "  \"pagination\": {\n" +
                        "    \"page\": 1,\n" +
                        "    \"size\": 10,\n" +
                        "    \"total\": 0\n" +
                        "  }\n" +
                        "}");
        this.mockMvc.perform(content2)
                .andExpect(ok)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status").value("ok"));

    }
}