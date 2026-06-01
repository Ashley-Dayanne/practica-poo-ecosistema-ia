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

## Fase 3 - Herencia

### Aplicación de la Herencia

Para evitar la duplicación de código y reutilizar la lógica común de los modelos de Inteligencia Artificial, la clase `ModeloIA` fue utilizada como superclase.

A partir de ella se implementaron las siguientes subclases:

* RedNeuronal
* ArbolDecision
* ModeloRegresion

Cada una hereda los atributos encapsulados y los métodos generales de entrenamiento y visualización de métricas.

La instrucción `super()` se utilizó en los constructores para inicializar correctamente el estado heredado de la superclase.

Asimismo, cada subclase sobrescribe el método `mostrarMetricas()` mediante `@Override`, llamando primero a `super.mostrarMetricas()` para mostrar la información general y posteriormente imprimiendo sus atributos específicos.

### Evidencia de ejecución

```text
Red Neuronal entrenado correctamente.
Árbol de Decisión entrenado correctamente.
Modelo de Regresión entrenado correctamente.

===== MÉTRICAS GENERALES =====
Nombre: Red Neuronal
Precisión: 54.0%
Épocas entrenadas: 2
Tasa de aprendizaje: 0.2
Capas ocultas: 5

===== MÉTRICAS GENERALES =====
Nombre: Árbol de Decisión
Precisión: 53.0%
Épocas entrenadas: 2
Tasa de aprendizaje: 0.15
Profundidad máxima: 20

===== MÉTRICAS GENERALES =====
Nombre: Modelo de Regresión
Precisión: 52.0%
Épocas entrenadas: 2
Tasa de aprendizaje: 0.1
Coeficiente de regularización: 0.01
```
