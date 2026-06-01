package com.ia.aplicacion;

import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloRegresion;
import com.ia.modelos.RedNeuronal;

public class SimuladorIA {

```
public static void main(String[] args) {

    RedNeuronal red = new RedNeuronal(
            "Red Neuronal",
            0.2,
            5
    );

    ArbolDecision arbol = new ArbolDecision(
            "Árbol de Decisión",
            0.15,
            20
    );

    ModeloRegresion regresion = new ModeloRegresion(
            "Modelo de Regresión",
            0.1,
            0.01
    );

    red.entrenar();
    arbol.entrenar();
    regresion.entrenar();

    red.entrenar();
    arbol.entrenar();
    regresion.entrenar();

    red.mostrarMetricas();
    arbol.mostrarMetricas();
    regresion.mostrarMetricas();
}
```

}
