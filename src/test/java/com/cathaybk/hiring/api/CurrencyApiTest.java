package com.cathaybk.hiring.api;

import com.cathaybk.hiring.HiringApplication;
import com.cathaybk.hiring.MockMvcUtil;
import com.cathaybk.hiring.model.Currency;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class CurrencyApiTest {

    @Autowired
    private MockMvc mockMvc;

    // 設定一個測試用的 code 不要跟真的資料撞到
    private static String testEnCode = "T_USD";

    @Order(1)
    @Test
    // 1. 測試呼叫查詢幣別對應表資料 API,並顯示其內容。
    public void getTest() throws Exception {
        // 先create
        RequestBuilder request = MockMvcUtil.getMockMvcRequest(HttpMethod.POST, "/currency", new Currency()
                .setZh("美元")
                .setEnCode(testEnCode)
                .setRate(23740.3717f)
                .setSymbol("&#36;")
                .setDescription("United States Dollar"));

        MvcResult res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // 再getAll
        request = MockMvcUtil.getMockMvcRequest(HttpMethod.GET, "/currency");
        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

        // 再get by id
        request = MockMvcUtil.getMockMvcRequest(HttpMethod.GET, "/currency/"+ testEnCode);
        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

    @Order(2)
    @Test
    // 2. 測試呼叫新增幣別對應表資料 API。
    public void createTest() throws Exception {
        RequestBuilder request = MockMvcUtil.getMockMvcRequest(HttpMethod.POST, "/currency", new Currency()
                .setZh("美元")
                .setEnCode(testEnCode)
                .setRate(23740.3717f)
                .setSymbol("&#36;")
                .setDescription("United States Dollar"));

        MvcResult res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

        request = MockMvcUtil.getMockMvcRequest(HttpMethod.POST, "/currency", new Currency()
                .setZh("美元")
                .setEnCode(testEnCode)
                .setRate(23740.3717f)
                .setSymbol("&#36;")
                .setDescription("這次該失敗 不能重複新增"));

        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

    }

    @Order(3)
    @Test
    // 3. 測試呼叫更新幣別對應表資料 API,並顯示其內容。
    public void updateTest() throws Exception {
        Currency currency = new Currency()
                .setZh("美元")
                .setEnCode(testEnCode)
                .setRate(23740.3717f)
                .setSymbol("&#36;")
                .setDescription("United States Dollar");
        // 先create
        RequestBuilder request = MockMvcUtil.getMockMvcRequest(HttpMethod.POST, "/currency", currency);

        MvcResult res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // 再update
        currency.setDescription("這是更新後的美元")
                .setZh("超級美元")
                .setRate(23740.3737f);
        request = MockMvcUtil.getMockMvcRequest(HttpMethod.PUT, "/currency", currency);
        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

        currency.setDescription("這是更新後的美元, 而且這次也要成功")
                .setZh("超級美元")
                .setRate(23740.3736f);
        request = MockMvcUtil.getMockMvcRequest(HttpMethod.PUT, "/currency", currency);
        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

    }

    @Order(4)
    @Test
    // 4. 測試呼叫刪除幣別對應表資料 API。
    public void deleteTest() throws Exception {
        // 先create
        RequestBuilder request = MockMvcUtil.getMockMvcRequest(HttpMethod.POST, "/currency", new Currency()
                .setZh("美元")
                .setEnCode(testEnCode)
                .setRate(23740.3717f)
                .setSymbol("&#36;")
                .setDescription("United States Dollar"));

        MvcResult res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // 再delete
        request = MockMvcUtil.getMockMvcRequest(HttpMethod.DELETE, "/currency/" + testEnCode);
        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());

        request = MockMvcUtil.getMockMvcRequest(HttpMethod.DELETE, "/currency/" + testEnCode);
        res = mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

}
