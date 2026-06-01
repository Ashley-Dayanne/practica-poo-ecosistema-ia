package com.ia.tokenizadores;

import com.ia.contratos.Tokenizador;

public class TokenizadorBasico implements Tokenizador {

    @Override
    public String[] dividirTexto(String parrafo) {

        return parrafo.split(" ");
    }
}
