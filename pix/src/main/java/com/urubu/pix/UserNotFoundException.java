package com.urubu.pix;

public class UserNotFoundException extends RuntimeException{
    
    UserNotFoundException(Long id) {
        super("Não encontramos o usuário de id " + id);
    }
}
