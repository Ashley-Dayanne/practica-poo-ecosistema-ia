package com.ia.modelos;

import com.ia.interfaces.Entrenable;

public class ArbolDecision extends ModeloIA implements Entrenable {

    private int profundidadMaxima;

    public ArbolDecision(String nombre, double tasaAprendizaje, int profundidadMaxima) {
        super(nombre, tasaAprendizaje);
        this.profundidadMaxima = profundidadMaxima;
    }

    // IMPLEMENTACIÓN OBLIGATORIA DEL MÉTODO ABSTRACTO DE MODELOIA
    @Override
    public void entrenar() {
        registrarEpoca();
        // Lógica basada en profundidad máxima
        double mejora = (profundidadMaxima * 0.5) + (getTasaAprendizaje() * 5);
        aumentarPrecision(mejora);
        System.out.println(getNombre() + " calculó la ganancia de información (Entropía).");
    }

    @Override
    public void ajustarPesos(double tasaAprendizaje) {
        aumentarPrecision(tasaAprendizaje * 50);
        System.out.println("ArbolDecision optimizó divisiones del árbol.");
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Profundidad máxima: " + profundidadMaxima);
        System.out.println("==============================");
    }
}
