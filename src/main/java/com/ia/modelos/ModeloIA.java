package com.ia.modelos;

public abstract class ModeloIA {

    protected void aumentarPrecision(double incremento) {

    precision += incremento;

    if (precision > 100.0) {
        precision = 100.0;
    }
}
    // Atributos privados (Encapsulación)
    private String nombre;
    private double precision;
    private int epocasEntrenadas;
    private double tasaAprendizaje;

    // Constructor
    public ModeloIA(String nombre, double tasaAprendizaje) {

        this.nombre = nombre;
        this.precision = 50.0;
        this.epocasEntrenadas = 0;

        // Validación inicial
        if (tasaAprendizaje > 0.0 && tasaAprendizaje < 1.0) {
            this.tasaAprendizaje = tasaAprendizaje;
        } else {
            System.out.println("Tasa inválida. Se asignará 0.1 por defecto.");
            this.tasaAprendizaje = 0.1;
        }
    }

    // Getters (solo lectura)

    public String getNombre() {
        return nombre;
    }

    public double getPrecision() {
        return precision;
    }

    public int getEpocasEntrenadas() {
        return epocasEntrenadas;
    }

    public double getTasaAprendizaje() {
        return tasaAprendizaje;
    }

    // Setter con validación
    public void setTasaAprendizaje(double tasa) {

        if (tasa > 0.0 && tasa < 1.0) {
            tasaAprendizaje = tasa;
            System.out.println("Tasa de aprendizaje actualizada correctamente.");
        } else {
            System.out.println("Error: la tasa debe ser mayor a 0 y menor a 1.");
        }
    }

    // Método entrenar
public abstract void entrenar();

    // Mostrar métricas
    public void mostrarMetricas() {

        System.out.println("\n===== MÉTRICAS DEL MODELO =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precisión: " + precision + "%");
        System.out.println("Épocas entrenadas: " + epocasEntrenadas);
        System.out.println("Tasa de aprendizaje: " + tasaAprendizaje);
        System.out.println("================================");
    }
}
