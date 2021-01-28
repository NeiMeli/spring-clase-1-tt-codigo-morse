package com.bootcamp.clase1ttcodigomorse.common;

public class TestConstants {
    public static String EMPTY_MESSAGE = "";
    public static String EMPTY_MESSAGE_WITH_SPACES = "    ";
    public static TestMessage MESSAGE_1 = new TestMessage(".... --- .-.. .-", "hola");
    public static TestMessage MESSAGE_2 = new TestMessage("-- . .-. -.-. .- -.. ---   .-.. .. -... .-. .", "mercado libre");
    public static TestMessage MESSAGE_3 = new TestMessage("... .--. .-. .. -. --.", "spring");
    public static TestMessage MESSAGE_4 = new TestMessage(". ... - .-   . ...   .-.. .-   .--. .-. .- -.-. - .. -.-. .-   -.. .   .-.. .-   - .- .-. -.. .   -.. .   .-.. .-   .--. .-. .. -- . .-. .-   -.-. .-.. .- ... .   -.. .   ... .--. .-. .. -. --.   . -.   -.. --- -. -.. .   . ... - .- -- --- ...   - .-. .- -.. ..- -.-. .. . -. -.. ---   -.-. --- -.. .. --. ---   -- --- .-. ... .   .-   .-.. . -. --. ..- .- .--- .   .- ... -.-. .. ..", "esta es la practica de la tarde de la primera clase de spring en donde estamos traduciendo codigo morse a lenguaje ascii");
    public static String EMPTY_MESSAGE_JSON = "{ \"value\": \"\"}";
    public static String EMPTY_MESSAGE_WITH_SPACES_JSON = "{ \"value\": \"      \"}";
    public static String INVALID_MESSAGE_JSON = "{ \"value\": \"... .--. .-. .. -. --.--..-.\"}";
}

