package com.ia.aplicacion;

import com.ia.interfaces.Entrenable;
import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;
import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloRegresion;
import com.ia.modelos.RedNeuronal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimuladorIA {

    public static void main(String[] args) {

        System.out.println("=== ENTRENAMIENTO POLIMÓRFICO ===");

        List<Entrenable> modelos = new ArrayList<>();

        RedNeuronal red =
                new RedNeuronal("Red Neuronal", 0.2, 5);

        ArbolDecision arbol =
                new ArbolDecision("Árbol de Decisión", 0.15, 20);

        ModeloRegresion regresion =
                new ModeloRegresion(
                        "Modelo de Regresión",
                        0.1,
                        0.01
                );

        modelos.add(red);
        modelos.add(arbol);
        modelos.add(regresion);

        for (Entrenable modelo : modelos) {

            modelo.ajustarPesos(0.02);
        }

        System.out.println("\n=== MÉTRICAS ===");

        red.mostrarMetricas();
        arbol.mostrarMetricas();
        regresion.mostrarMetricas();

        System.out.println("\n=== TOKENIZACIÓN ===");

        String texto =
                "La inteligencia artificial transforma industrias";

        Tokenizador miTokenizador =
                new TokenizadorBasico();

        System.out.println(
                "Tokenizador Básico:"
        );

        System.out.println(
                Arrays.toString(
                        miTokenizador.dividirTexto(texto)
                )
        );

        miTokenizador =
                new TokenizadorHuggingFace();

        System.out.println(
                "Tokenizador HuggingFace:"
        );

        System.out.println(
                Arrays.toString(
                        miTokenizador.dividirTexto(texto)
                )
        );
    }
}
