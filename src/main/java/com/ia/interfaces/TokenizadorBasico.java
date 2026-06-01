package com.ia.interfaces;

import com.ia.interfaces.Tokenizador;

public class TokenizadorBasico implements Tokenizador {

    @Override
    public String[] dividirTexto(String parrafo) {

        return parrafo.split(" ");
    }
}
