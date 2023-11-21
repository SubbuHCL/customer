package com.example.demo.exception;

import java.util.List;

import org.springframework.validation.ObjectError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private List<ObjectError> errors;
    
}
