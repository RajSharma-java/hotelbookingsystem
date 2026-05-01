package com.tech_raj.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class ErrorResponse {
    private String msg;
    private int status;
    private LocalDateTime localDateTime;

    public ErrorResponse(String msg, int status, LocalDateTime localDateTime) {
        this.msg = msg;
        this.status = status;
        this.localDateTime = LocalDateTime.now();
    }
}
