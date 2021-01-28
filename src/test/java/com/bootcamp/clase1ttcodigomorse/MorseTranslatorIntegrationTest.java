package com.bootcamp.clase1ttcodigomorse;

import com.bootcamp.clase1ttcodigomorse.service.MorseTranslatorServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.bootcamp.clase1ttcodigomorse.common.TestConstants.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MorseTranslatorIntegrationTest {

    private static final String PATH = "/morse/translate";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFailsBecauseOfEmptyMessage() throws Exception {
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(EMPTY_MESSAGE_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(MorseTranslatorServiceException.MorseTranslatorError.EMPTY_MESSAGE.getValue())));
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(EMPTY_MESSAGE_WITH_SPACES_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(MorseTranslatorServiceException.MorseTranslatorError.EMPTY_MESSAGE.getValue())));
    }

    @Test
    void testFailsBecauseOfInvalidMessage() throws Exception {
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(INVALID_MESSAGE_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(String.format(MorseTranslatorServiceException.MorseTranslatorError.INVALID_MORSE_CHARACTER.getValue(), ""))));
    }

    @Test
    void testHappy() throws Exception {
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(MESSAGE_1.toJson()))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(containsString(MESSAGE_1.translation)));
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(MESSAGE_2.toJson()))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(containsString(MESSAGE_2.translation)));
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(MESSAGE_3.toJson()))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(containsString(MESSAGE_3.translation)));
        mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON).content(MESSAGE_4.toJson()))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(containsString(MESSAGE_4.translation)));
    }
}