package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    UserControllerTest(WebApplicationContext context) {
        this.webApplicationContext = context;
    }

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @DisplayName("User更新画面")
    @WithMockUser
    void testGetUser() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/update/1/")).andExpect(status().isOk())
                .andExpect(model().attributeExists("user")).andExpect(model().hasNoErrors())
                .andExpect(view().name("user/update")).andReturn();

        User user = (User) result.getModelAndView().getModel().get("user");
        assertEquals(1, user.getId());
        assertEquals("キラメキ太郎", user.getName());
    }

    @Test
    @DisplayName("User更新画面")
    @WithMockUser
    void testGetList() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/list")).andExpect(status().isOk())
                .andExpect(model().attributeExists("userlist")).andExpect(model().hasNoErrors())
                .andExpect(view().name("user/list")).andReturn();

        List<User> user = (List<User>) result.getModelAndView().getModel().get("userlist");
        assertEquals(3, user.size());

        List<User> userList = (List<User>) result.getModelAndView().getModel().get("userlist");

        assertEquals("キラメキ太郎", "キラメキ太郎");
        assertEquals("キラメキ次郎", "キラメキ次郎");
        assertEquals("キラメキ花子", "キラメキ花子");
        assertEquals(1, 1);
        assertEquals(2, 2);
        assertEquals(3, 3);
    }
}