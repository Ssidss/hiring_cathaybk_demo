package com.cathaybk.hiring.api;

import com.cathaybk.hiring.HiringApplication;
import com.cathaybk.hiring.MockMvcUtil;
import com.cathaybk.hiring.model.Currency;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HiringApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class CoindeskApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    // 6. 測試呼叫資料轉換的 API,並顯示其內容。
    public void getTest() throws Exception {
        // 先create
        RequestBuilder request = MockMvcUtil.getMockMvcRequest(HttpMethod.GET, "/coindesk/newGetCurrentPrice");

        MvcResult res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

}
