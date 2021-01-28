package com.bootcamp.clase1ttcodigomorse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bootcamp.clase1ttcodigomorse.common.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class MorseTranslatorServiceTest {

    @Autowired
    MorseTranslatorService service;

    @Test
    void translateEmptyMessageException() {
        assertThatExceptionOfType(MorseTranslatorServiceException.class).isThrownBy(() -> service.translateMessage(EMPTY_MESSAGE)).withMessageContaining(MorseTranslatorServiceException.MorseTranslatorError.EMPTY_MESSAGE.getValue());
        assertThatExceptionOfType(MorseTranslatorServiceException.class).isThrownBy(() -> service.translateMessage(EMPTY_MESSAGE_WITH_SPACES)).withMessageContaining(MorseTranslatorServiceException.MorseTranslatorError.EMPTY_MESSAGE.getValue());
    }

    @Test
    void translateMessageHappy() throws MorseTranslatorServiceException {
        assertThat(service.translateMessage(MESSAGE_1.morse)).isEqualTo(MESSAGE_1.translation);
        assertThat(service.translateMessage(MESSAGE_2.morse)).isEqualTo(MESSAGE_2.translation);
        assertThat(service.translateMessage(MESSAGE_3.morse)).isEqualTo(MESSAGE_3.translation);
        assertThat(service.translateMessage(MESSAGE_4.morse)).isEqualTo(MESSAGE_4.translation);
    }

    @Test
    void translateMessageFailsBecauseOfInvalidCharacter() {
        final String invalidMessage1 = MESSAGE_1.morse + ".,-@";
        final String invalidMessage2 = MESSAGE_1.morse + "       " + MESSAGE_2.morse;
        assertThatExceptionOfType(MorseTranslatorServiceException.class).isThrownBy(() -> service.translateMessage(invalidMessage1)).withMessageContaining(String.format(MorseTranslatorServiceException.MorseTranslatorError.INVALID_MORSE_CHARACTER.getValue(), ""));
        assertThatExceptionOfType(MorseTranslatorServiceException.class).isThrownBy(() -> service.translateMessage(invalidMessage2)).withMessageContaining(String.format(MorseTranslatorServiceException.MorseTranslatorError.INVALID_MORSE_CHARACTER.getValue(), ""));
    }
}