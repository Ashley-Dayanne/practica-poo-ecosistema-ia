package com.ia.modelos;

import com.ia.interfaces.Entrenable;

public final class RedNeuronal extends ModeloIA implements Entrenable { ... }

    private int capasOcultas;

    public RedNeuronal(String nombre, double tasaAprendizaje, int capasOcultas) {
        super(nombre, tasaAprendizaje);
        this.capasOcultas = capasOcultas;
    }

    // IMPLEMENTACIÓN OBLIGATORIA DEL MÉTODO ABSTRACTO DE MODELOIA
    @Override
    public void entrenar() {
        registrarEpoca();
        // Lógica matemática simulada basada en capas ocultas
        double mejora = getTasaAprendizaje() * 12 * capasOcultas;
        aumentarPrecision(mejo_ra);
        System.out.println(getNombre() + " ejecutó entrenamiento Forward/Backward propagation.");
    }

    @Override
    public void ajustarPesos(double tasaAprendizaje) {
        aumentarPrecision(tasaAprendizaje * 100);
        System.out.println("RedNeuronal ajustó pesos usando backpropagation.");
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Capas ocultas: " + capasOcultas);
        System.out.println("==============================");
    }
}
