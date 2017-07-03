/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.exhandler.exceptions;

public class BookNotFoundException extends RuntimeException {
 
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BookNotFoundException(){
        super();
    }
    
    public BookNotFoundException(String message) {
        super(message);
    }
    
    public BookNotFoundException(Throwable cause) {
        super(cause);
    }
}
