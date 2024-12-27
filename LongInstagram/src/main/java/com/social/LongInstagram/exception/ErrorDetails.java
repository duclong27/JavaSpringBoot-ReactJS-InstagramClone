package com.social.LongInstagram.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;




@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {

    private String message;

    private String details;

    private LocalDateTime timeStamp;

    public ErrorDetails() {
    }
}
