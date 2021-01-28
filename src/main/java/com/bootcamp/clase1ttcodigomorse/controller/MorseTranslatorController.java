package com.bootcamp.clase1ttcodigomorse.controller;

import com.bootcamp.clase1ttcodigomorse.dto.MorseMessage;
import com.bootcamp.clase1ttcodigomorse.service.MorseTranslatorService;
import com.bootcamp.clase1ttcodigomorse.service.MorseTranslatorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/morse")
public class MorseTranslatorController {
    @Autowired
    MorseTranslatorService service;

    @PostMapping(value = "/translate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> translateMorse (@RequestBody MorseMessage message) {
        String response;
        HttpStatus status;
        try {
            response = service.translateMessage(message.getValue());
            status = HttpStatus.OK;
        } catch (final Exception e) {
            if (e instanceof MorseTranslatorServiceException) {
                status = HttpStatus.BAD_REQUEST;
            } else status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = e.getMessage();
        }
        return new ResponseEntity<>(response, status);
    }
}
