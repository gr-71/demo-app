package com.example.app.exceptions;

import lombok.Data;
import java.util.Date;

@Data
public class DemoAppApplicationError {
    private int status;
    private String message;
    private Date timestamp;

    public DemoAppApplicationError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
