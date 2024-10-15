package com.pokemon.api.exceptions;


import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {

    private Integer statusCode;
    private String message;
    private Date timeStamp;

    //The purpose of the class os that it gives the error a proper body for
    //presenting the error in a proper format
}
