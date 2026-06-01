package com.ia.modelos;

public class ArbolDecision extends ModeloIA implements Entrenable {

```
private int profundidadMaxima;

public ArbolDecision(String nombre,
                     double tasaAprendizaje,
                     int profundidadMaxima) {

    super(nombre, tasaAprendizaje);
    this.profundidadMaxima = profundidadMaxima;
}

@Override
public void mostrarMetricas() {

    super.mostrarMetricas();

    System.out.println("Profundidad máxima: " + profundidadMaxima);
    System.out.println("==============================");
}
```
@Override
public void ajustarPesos(double tasaAprendizaje) {

    aumentarPrecision(tasaAprendizaje * 50);

    System.out.println(
            "ArbolDecision optimizó divisiones del árbol."
    );
}
}
