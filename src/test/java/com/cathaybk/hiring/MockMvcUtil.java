package com.cathaybk.hiring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

public class MockMvcUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static MockHttpServletRequestBuilder getMockMvcRequest(HttpMethod httpMethod, String url) throws JsonProcessingException {
        return getMockMvcRequest(httpMethod, url, null);
    }

    public static <TD> MockHttpServletRequestBuilder getMockMvcRequest(HttpMethod httpMethod, String url, TD requestBody) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("User-Agent", "MockMvc Test");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder mockMvcRequestBuilders = MockMvcRequestBuilders.request(httpMethod, url)
                .headers(httpHeaders);
        if (requestBody != null) {
            mockMvcRequestBuilders = mockMvcRequestBuilders.content(objectMapper.writeValueAsString(requestBody));
        }

        return mockMvcRequestBuilders;

    }

}
