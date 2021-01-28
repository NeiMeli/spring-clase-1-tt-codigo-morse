package com.bootcamp.clase1ttcodigomorse.dto;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class MorseMessage implements Serializable {
    @NotNull private String value;

    public MorseMessage() {}

    public @NotNull String getValue() {
        return value;
    }
}
