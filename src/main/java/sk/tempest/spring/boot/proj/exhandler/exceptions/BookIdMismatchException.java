/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tempest.spring.boot.proj.exhandler.exceptions;

public class BookIdMismatchException extends RuntimeException{
    
    public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BookIdMismatchException(){
        super();
    }
    
    public BookIdMismatchException(String message) {
        super(message);
    }
    
    public BookIdMismatchException(Throwable cause) {
        super(cause);
    }
    
}
