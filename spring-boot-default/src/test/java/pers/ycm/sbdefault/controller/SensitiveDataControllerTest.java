package pers.ycm.sbdefault.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author yuanchengman
 * @date 2021-01-25
 */
public class SensitiveDataControllerTest extends AbstractControllerTest {
    /**
     * 测试数据脱敏，接口返回时特定字段用*过滤。
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/sensitivedata/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
        );

        super.expect(resultActions);

        resultActions.andExpect(
                MockMvcResultMatchers.jsonPath("$.data.name", Matchers.contains("*"))
        );
    }
}
