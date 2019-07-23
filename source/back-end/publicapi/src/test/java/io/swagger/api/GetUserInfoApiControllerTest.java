package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* GetUserInfoApiController Tester. 
* 
* @author <Authors name> 
* @since <pre>Jul 20, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class GetUserInfoApiControllerTest extends QueryApiBaseTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getUserInfoPost(@ApiParam(value = "") @Valid @RequestBody UserId body) 
* 
*/ 
@Test
public void testGetUserInfoPost() throws Exception { 
//TODO: Test goes here... 
} 


}
