package com.ia.exceptions;

public class IAComponentException extends RuntimeException {
    
    // Constructor que acepta el mensaje de error explicativo
    public IAComponentException(String mensaje) {
        super(mensaje);
    }
}
