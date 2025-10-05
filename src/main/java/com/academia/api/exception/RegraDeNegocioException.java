/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Kayqu
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String message) {
        super(message);
    }
}
