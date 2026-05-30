# practica-poo-ecosistema-ia
Repositorio con los códigos del tercer parcial de la materia Programación Orientada a Objetos

## Fase 2 - Encapsulación

### Aplicación de la Encapsulación

La clase `ModeloIA` implementa el principio de encapsulación mediante atributos privados:

* nombre
* precision
* epocasEntrenadas
* tasaAprendizaje

Los atributos `precision` y `epocasEntrenadas` únicamente pueden consultarse mediante métodos getter, evitando modificaciones externas directas.

La tasa de aprendizaje se modifica exclusivamente mediante el método `setTasaAprendizaje()`, el cual valida que el valor se encuentre en el rango permitido `(0.0 < tasa < 1.0)`.

Además, el método `entrenar()` es el único responsable de actualizar las métricas internas del modelo, garantizando la integridad de los datos durante la simulación.

### Evidencia de ejecución

```text
Intentando asignar tasa inválida...
Error: la tasa debe ser mayor a 0 y menor a 1.

=== INICIANDO ENTRENAMIENTO ===

Entrenamiento #1
RedNeuronal fue entrenado correctamente.
ArbolDecision fue entrenado correctamente.

...

=== RESULTADOS FINALES ===

Nombre: RedNeuronal
Precisión: 60.0%
Épocas entrenadas: 5

Nombre: ArbolDecision
Precisión: 57.5%
Épocas entrenadas: 5
```
