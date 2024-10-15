package com.pokemon.api.exceptions;

public class PokemonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;


    public PokemonNotFoundException(String message){

        super(message);

        //super method passes up the message to the actual RuntimeException
        //so ,whenever we write our exception and it gets triggered super transmit that message to main RuntimeException

    }



}
