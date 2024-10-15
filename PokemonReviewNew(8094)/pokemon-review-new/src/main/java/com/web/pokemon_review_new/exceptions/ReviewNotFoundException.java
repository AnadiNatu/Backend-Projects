package com.web.pokemon_review_new.exceptions;

public class ReviewNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2;

    public ReviewNotFoundException(String message){

        super(message);

        //super method passes up the message to the actual RuntimeException
        //so ,whenever we write our exception and it gets triggered super transmit that message to main RuntimeException

    }
}
