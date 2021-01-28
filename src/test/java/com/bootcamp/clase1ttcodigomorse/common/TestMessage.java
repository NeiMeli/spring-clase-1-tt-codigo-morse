package com.bootcamp.clase1ttcodigomorse.common;

public class TestMessage {
    public String morse;
    public String translation;

    public TestMessage(String morse, String translation) {
        this.morse = morse;
        this.translation = translation;
    }

    public String toJson() {
        return String.format("{\"value\": \"%s\"}", morse);
    }
}
