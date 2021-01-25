package pers.ycm.sbdefault.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pers.ycm.sbdefault.ServiceInitializer;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

/**
 * @author yuanchengman
 * @date 2021-01-04
 */
@SpringBootTest(classes = ServiceInitializer.class)
@RunWith(SpringRunner.class)
public abstract class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 基础的断言
     *
     * @param resultActions
     * @return
     * @throws Exception
     */
    protected ResultActions expect(@NotNull ResultActions resultActions) throws Exception {
        resultActions.andReturn().getResponse().setCharacterEncoding(StandardCharsets.UTF_8.toString());

        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(1)));

        return resultActions;
    }
}
