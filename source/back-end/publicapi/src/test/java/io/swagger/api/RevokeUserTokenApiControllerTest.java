package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class RevokeUserTokenApiControllerTest extends QueryApiBaseTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void revokeUserTokenPost() {
    }
}