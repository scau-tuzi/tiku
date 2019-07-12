package io.swagger.service;

import io.swagger.Swagger2SpringBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ProblemServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jul 10, 2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
@ComponentScan(basePackages = {"cn", "io", "test"})
public class ProblemServiceImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryProblem(Expression expression)
     */
//@Autowired
//private ProblemTagRepository problemTagRepository;
    @Autowired
    private ProblemService problemService;

    @Test
    public void testQueryProblem() throws Exception {
        //problemTagRepository.findAll();
        problemService.queryProblem(null);
//TODO: Test goes here... 
    }


} 
