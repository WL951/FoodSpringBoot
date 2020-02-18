package com.wl.foodspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoodSpringBootApplication.class})
@AutoConfigureMockMvc   //模仿一个http请求
public class RequestMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() throws Exception{
//        perform：执行一个RequestBuilder请求
//        andExpect：添加ResultMatcher->MockMvcResultMatchers验证规则
//        andReturn：最后返回相应的MvcResult->Response
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/test/test")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
        String contentAsString = response.getContentAsString();//获取返回 @ResponseBody json字符串 : 进行反序列化处理即可
        System.out.println(contentAsString);
    }
}
