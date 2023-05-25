package com.it.academy.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Builder
@Data
public class AppErrorResponse {
    private String message;
    private String timestamp;
}