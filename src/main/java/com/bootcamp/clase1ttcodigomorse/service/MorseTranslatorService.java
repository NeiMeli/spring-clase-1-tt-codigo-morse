package com.bootcamp.clase1ttcodigomorse.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MorseTranslatorService {
    private static final String BLANK_STRING = " ";
    private static final Map<String, String> map = new HashMap<>();
    private static final String MORSE_BLANK = " {3}";
    private static final String CHARACTER_SEPARATOR = " ";

    static {
        map.put(".-", "a");
        map.put("-...", "b");
        map.put("-.-.", "c");
        map.put("-..", "d");
        map.put(".", "e");
        map.put("..-.", "f");
        map.put("--.", "g");
        map.put("....", "h");
        map.put("..", "i");
        map.put(".---", "j");
        map.put("-.-", "k");
        map.put(".-..", "l");
        map.put("--", "m");
        map.put("-.", "n");
        map.put("---", "o");
        map.put(".--.", "p");
        map.put("--.-", "q");
        map.put(".-.", "r" );
        map.put("...", "s");
        map.put("-", "t");
        map.put("..-", "u");
        map.put("...-", "v");
        map.put(".--", "w");
        map.put("-..-", "x");
        map.put("-.--", "y");
        map.put("--..", "z");
        map.put(".----", "1");
        map.put("..---", "2");
        map.put("...--", "3");
        map.put("....-", "4");
        map.put(".....", "5");
        map.put( "-....", "6");
        map.put("--...", "7");
        map.put("---..", "8");
        map.put("----.", "9");
        map.put("-----", "0");
    }

    public String translateMessage(final @NotNull String message) throws MorseTranslatorServiceException {
        if (message.trim().isEmpty())
            throw new MorseTranslatorServiceException(MorseTranslatorServiceException.MorseTranslatorError.EMPTY_MESSAGE);
        final List<String> wordsInMorse = extractWords(message);
        final List<String> translatedWords = new ArrayList<>();
        for (String wordInMorse : wordsInMorse) {
            translatedWords.add(translateWord(wordInMorse));
        }
        return String.join(BLANK_STRING, translatedWords);
    }

    public List<String> extractWords(String message) {
        return Arrays.asList(message.split(MORSE_BLANK));
    }

    public String translateWord(String wordInMorse) throws MorseTranslatorServiceException {
        String[] morseCharacters = wordInMorse.split(CHARACTER_SEPARATOR);
        StringBuilder translatedCharacters = new StringBuilder();
        for (String morseCharacter : morseCharacters) {
            translatedCharacters.append(translateCharacter(morseCharacter));
        }
        return translatedCharacters.toString();
    }

     public String translateCharacter(String characterInMorse) throws MorseTranslatorServiceException {
        if (map.containsKey(characterInMorse))
            return map.get(characterInMorse);
        else throw new MorseTranslatorServiceException(String.format(MorseTranslatorServiceException.MorseTranslatorError.INVALID_MORSE_CHARACTER.getValue(), characterInMorse));
     }
}
