package com.ia.modelos;

public abstract class ModeloIA {

    // Cambiamos a protected para que las subclases puedan modificar la precisión directamente
    protected double precision; 
    private String nombre;
    private int epocasEntrenadas;
    private double tasaAprendizaje;

    public ModeloIA(String nombre, double tasaAprendizaje) {
        this.nombre = nombre;
        this.precision = 50.0;
        this.epocasEntrenadas = 0;

        if (tasaAprendizaje > 0.0 && tasaAprendizaje < 1.0) {
            this.tasaAprendizaje = tasaAprendizaje;
        } else {
            System.out.println("Tasa inválida. Se asignará 0.1 por defecto.");
            this.tasaAprendizaje = 0.1;
        }
    }

    // MÉTODO ABSTRACTO: No lleva llaves {}, termina en punto y coma.
    public abstract void entrenar();

    protected void aumentarPrecision(double incremento) {
        precision += incremento;
        if (precision > 100.0) {
            precision = 100.0;
        }
    }

    public void registrarEpoca() {
        this.epocasEntrenadas++;
    }

    // --- Getters y Setters ---
    public String getNombre() { return nombre; }
    public double getPrecision() { return precision; }
    public int getEpocasEntrenadas() { return epocasEntrenadas; }
    public double getTasaAprendizaje() { return tasaAprendizaje; }

    public void setTasaAprendizaje(double tasa) {
        if (tasa > 0.0 && tasa < 1.0) {
            tasaAprendizaje = tasa;
        } else {
            System.out.println("Error: la tasa debe ser entre 0 y 1.");
        }
    }

    public void mostrarMetricas() {
        System.out.println("\n===== MÉTRICAS DEL MODELO =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precisión: " + precision + "%");
        System.out.println("Épocas entrenadas: " + epocasEntrenadas);
        System.out.println("Tasa de aprendizaje: " + tasaAprendizaje);
    }
}
