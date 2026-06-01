package com.ia.modelos;

public class ArbolDecision extends ModeloIA {

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

}
