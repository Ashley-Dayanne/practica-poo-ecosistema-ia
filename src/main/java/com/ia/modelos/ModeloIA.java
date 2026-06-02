package com.ia.modelos;

// Importamos la excepción personalizada
import com.ia.exceptions.IAComponentException;

public abstract class ModeloIA {

    protected double precision; 
    private String nombre;
    private int epocasEntrenadas;
    private double tasaAprendizaje;

    public ModeloIA(String nombre, double tasaAprendizaje) {
        this.nombre = nombre;
        this.precision = 50.0;
        this.epocasEntrenadas = 0;

        // Modificamos para lanzar excepción si la tasa inicial es inválida
        if (tasaAprendizaje > 0.0 && tasaAprendizaje < 1.0) {
            this.tasaAprendizaje = tasaAprendizaje;
        } else {
            throw new IAComponentException("Error: La tasa de aprendizaje inicial " + tasaAprendizaje + " está fuera del rango permitido (0.0 - 1.0).");
        }
    }

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

    // REFACTORIZACIÓN CON 'THROW'
    public void setTasaAprendizaje(double tasa) {
        if (tasa > 0.0 && tasa < 1.0) {
            tasaAprendizaje = tasa;
        } else {
            // Ya no imprimimos en consola, ahora LANZAMOS el error formalmente
            throw new IAComponentException("Error: La tasa de aprendizaje " + tasa + " está fuera del rango permitido (0.0 - 1.0).");
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
