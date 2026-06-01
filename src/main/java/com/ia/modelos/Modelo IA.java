package com.ia.modelos;

public class ModeloIA {

```
private String nombre;
private double precision;
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

public void setTasaAprendizaje(double tasa) {

    if (tasa > 0.0 && tasa < 1.0) {
        tasaAprendizaje = tasa;
    } else {
        System.out.println("Error: tasa inválida.");
    }
}

public void entrenar() {

    epocasEntrenadas++;

    double mejora = tasaAprendizaje * 10;

    precision += mejora;

    if (precision > 100.0) {
        precision = 100.0;
    }

    System.out.println(nombre + " entrenado correctamente.");
}

public void mostrarMetricas() {

    System.out.println("\n===== MÉTRICAS GENERALES =====");
    System.out.println("Nombre: " + nombre);
    System.out.println("Precisión: " + precision + "%");
    System.out.println("Épocas entrenadas: " + epocasEntrenadas);
    System.out.println("Tasa de aprendizaje: " + tasaAprendizaje);
}
```

}
