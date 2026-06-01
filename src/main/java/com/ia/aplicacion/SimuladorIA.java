package com.ia.aplicacion;

import com.ia.interfaces.Entrenable;
import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;
import com.ia.modelos.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimuladorIA {

    public static void main(String[] args) {

        // ==========================================
        // VALIDACIÓN DE LA ABSTRACCIÓN
        // ==========================================
        // Si descomentas la línea de abajo, Java dará un error de compilación:
        // ModeloIA modeloIncompleto = new ModeloIA("Genérico", 0.1); 
        // "Cannot instantiate the type ModeloIA"
        
        System.out.println("=== 1. ENTRENAMIENTO DIRIGIDO POR ABSTRACCIÓN ===");

        // Uso de Referencias Abstractas en colecciones
        ModeloIA[] modelosParaEntrenar = new ModeloIA[3];
        modelosParaEntrenar[0] = new RedNeuronal("Red Neuronal Convolucional", 0.05, 5);
        modelosParaEntrenar[1] = new ArbolDecision("Árbol de Fraudes", 0.1, 15);
        modelosParaEntrenar[2] = new ModeloRegresion("Regresión Logística", 0.2, 0.001);

        // Ciclo polimórfico usando la abstracción de la superclase
        for (ModeloIA modelo : modelosParaEntrenar) {
            modelo.entrenar();
            modelo.mostrarMetricas();
        }

        System.out.println("\n=== 2. AJUSTE DE PESOS (POLIMORFISMO INTERFACES) ===");
        List<Entrenable> listaEntrenables = new ArrayList<>();
        listaEntrenables.add((Entrenable) modelosParaEntrenar[0]);
        listaEntrenables.add((Entrenable) modelosParaEntrenar[1]);
        listaEntrenables.add((Entrenable) modelosParaEntrenar[2]);

        for (Entrenable e : listaEntrenables) {
            e.ajustarPesos(0.02);
        }

        System.out.println("\n=== 3. PIPELINE DE PROCESAMIENTO DE TEXTO ===");
        String texto = "La inteligencia artificial transforma industrias";

        Tokenizador miTokenizador = new TokenizadorBasico();
        System.out.println("Tokenizador Básico:");
        System.out.println(Arrays.toString(miTokenizador.dividirTexto(texto)));

        miTokenizador = new TokenizadorHuggingFace();
        System.out.println("Tokenizador HuggingFace:");
        System.out.println(Arrays.toString(miTokenizador.dividirTexto(texto)));
    }
}
