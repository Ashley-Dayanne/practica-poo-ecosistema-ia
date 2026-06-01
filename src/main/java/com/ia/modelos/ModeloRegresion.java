package com.ia.modelos;

public class ModeloRegresion extends ModeloIA implements Entrenable {

```
private double coeficienteRegularizacion;

public ModeloRegresion(String nombre,
                       double tasaAprendizaje,
                       double coeficienteRegularizacion) {

    super(nombre, tasaAprendizaje);
    this.coeficienteRegularizacion = coeficienteRegularizacion;
}

@Override
public void mostrarMetricas() {

    super.mostrarMetricas();

    System.out.println(
            "Coeficiente de regularización: "
            + coeficienteRegularizacion
    );

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
