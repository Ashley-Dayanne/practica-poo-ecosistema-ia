package com.ia.modelos;

public class RedNeuronal extends ModeloIA {

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

}
