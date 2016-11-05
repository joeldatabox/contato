/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.contato.exception;

/**
 *
 * @author Joel Rodrigues
 */
public class ContatoException extends Exception{

    public ContatoException() {
    }

    public ContatoException(String message) {
        super(message);
    }

    public ContatoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContatoException(Throwable cause) {
        super(cause);
    }

    public ContatoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
