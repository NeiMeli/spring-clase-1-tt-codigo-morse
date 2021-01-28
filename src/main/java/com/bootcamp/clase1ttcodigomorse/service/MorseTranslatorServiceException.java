package com.bootcamp.clase1ttcodigomorse.service;

public class MorseTranslatorServiceException extends Exception {
    public MorseTranslatorServiceException(String message) {
        super(message);
    }

    public MorseTranslatorServiceException(MorseTranslatorError error) {
        super(error.getValue());
    }

    public enum MorseTranslatorError {
        EMPTY_MESSAGE ("Mensaje vacio"),
        INVALID_MORSE_CHARACTER ("%s no es un caracter morse");

        private final String value;

        MorseTranslatorError(String s) {
            value = s;
        }

        public String getValue() {
            return value;
        }
    }
}
