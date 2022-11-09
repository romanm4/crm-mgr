package com.crm.mgr.test.tool;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthTestTool {
    public static String obtainAccessToken(MockMvc mockMvc, String login, String password) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("login", login);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(get("/api/v1/jwt/sign-in")
                        .params(params))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        return "Bearer " + result.andReturn().getResponse().getContentAsString();
    }
}
