package com.bootcamp.clase1ttcodigomorse.controller;

import com.bootcamp.clase1ttcodigomorse.service.MorseTranslatorService;
import com.bootcamp.clase1ttcodigomorse.service.MorseTranslatorServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.bootcamp.clase1ttcodigomorse.common.TestConstants.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MorseTranslatorControllerTest {
        private static final String PATH = "/morse/translate";
        private static final String ERROR = "error";

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        MorseTranslatorService service;

        @Test
        void testFailsBadRequest() throws Exception {
            when(service.translateMessage(any())).thenThrow(new MorseTranslatorServiceException(ERROR));
            mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(EMPTY_MESSAGE_JSON))
                    .andDo(print())
                    .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(content().string(containsString(ERROR)));
        }

        @Test
        void testFailsInternalServerError() throws Exception {
            when(service.translateMessage(any())).thenThrow(new RuntimeException(ERROR));
            mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(EMPTY_MESSAGE_JSON))
                    .andDo(print())
                    .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .andExpect(content().string(containsString(ERROR)));
        }

        @Test
        void testHappy() throws Exception {
            when(service.translateMessage(any())).thenReturn(MESSAGE_1.translation);
            mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(MESSAGE_1.toJson()))
                    .andDo(print())
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(content().string(containsString(MESSAGE_1.translation)));
        }
}
