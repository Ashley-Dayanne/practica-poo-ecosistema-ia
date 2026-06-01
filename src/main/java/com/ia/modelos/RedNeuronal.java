package com.ia.modelos;

import com.ia.interfaces.Entrenable;

public class RedNeuronal extends ModeloIA implements Entrenable{

```
private int capasOcultas;

public RedNeuronal(String nombre,
                   double tasaAprendizaje,
                   int capasOcultas) {

    super(nombre, tasaAprendizaje);
    this.capasOcultas = capasOcultas;
}

@Override
public void mostrarMetricas() {

    super.mostrarMetricas();

    System.out.println("Capas ocultas: " + capasOcultas);
    System.out.println("==============================");
}
```
@Override
public void ajustarPesos(double tasaAprendizaje) {

    aumentarPrecision(tasaAprendizaje * 100);

    System.out.println(
            "RedNeuronal ajustó pesos usando backpropagation."
    );
}
}
