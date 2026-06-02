package com.ia.modelos;

import com.ia.interfaces.Entrenable;

public final class ModeloRegresion extends ModeloIA implements Entrenable { ... }

    private double coeficienteRegularizacion;

    public ModeloRegresion(String nombre, double tasaAprendizaje, double coeficienteRegularizacion) {
        super(nombre, tasaAprendizaje);
        this.coeficienteRegularizacion = coeficienteRegularizacion;
    }

    // IMPLEMENTACIÓN OBLIGATORIA DEL MÉTODO ABSTRACTO DE MODELOIA
    @Override
    public void entrenar() {
        registrarEpoca();
        // Lógica matemática basada en regularización
        double mejora = (1.0 / (coeficienteRegularizacion + 1)) * getTasaAprendizaje() * 8;
        aumentarPrecision(mejora);
        System.out.println(getNombre() + " minimizó el error cuadrático medio (MSE).");
    }

    @Override
    public void ajustarPesos(double tasaAprendizaje) {
        aumentarPrecision(tasaAprendizaje * 40);
        System.out.println("ModeloRegresion aplicó regularización de crestas/Lasso.");
    }

    @Override
    public void mostrarMetricas() {
        super.mostrarMetricas();
        System.out.println("Coeficiente de regularización: " + coeficienteRegularizacion);
        System.out.println("==============================");
    }
}
