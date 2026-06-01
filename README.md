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
## Fase 4 - Polimorfismo

### Aplicación de Interfaces

Se implementó la interfaz `Entrenable` para desacoplar el proceso de optimización de los modelos concretos.

Las clases `RedNeuronal`, `ArbolDecision` y `ModeloRegresion` implementan el mismo contrato, permitiendo que el simulador las procese de forma uniforme mediante una colección de tipo `List<Entrenable>`.

También se implementó la interfaz `Tokenizador`, permitiendo intercambiar dinámicamente distintos algoritmos de procesamiento de texto sin modificar la lógica principal del simulador.

### Evidencia de ejecución

```text
=== ENTRENAMIENTO POLIMÓRFICO ===

RedNeuronal ajustó pesos usando backpropagation.
ArbolDecision optimizó divisiones del árbol.
ModeloRegresion actualizó coeficientes.

=== TOKENIZACIÓN ===
```

## Fase 5: Abstracción Mediante Clases Abstractas

En esta fase se refactorizó la superclase `ModeloIA` para convertirla en una **Clase Abstracta**, lo que impide por completo la existencia de modelos de IA genéricos e incompletos dentro del simulador. En un entorno real, no se puede ejecutar un "Modelo" sin un algoritmo físico real detrás. Al marcar el método `public abstract void entrenar();` sin cuerpo, obligamos contractualmente a que cada subclase (`RedNeuronal`, `ArbolDecision`, `ModeloRegresion`) defina obligatoriamente su propia lógica matemática y simulación interna de entrenamiento.

### Diferencia entre Clase Abstracta e Interfaz en nuestro Pipeline
* **Clase Abstracta (`ModeloIA`):** Modela una relación de identidad ("es un"). Comparte tanto estructura (atributos encapsulados como `nombre`, `precision`) como comportamiento común (`mostrarMetricas()`), delegando únicamente algoritmos específicos a los hijos.
* **Interfaz (`Tokenizador`):** Modela un contrato puro de comportamiento ("puede hacer"). No comparte identidades ni estados, simplemente define una capa abstracta pura de procesamiento intercambiable en tiempo de ejecución.

### Evidencia de ejecución
```text
=== 1. ENTRENAMIENTO DIRIGIDO POR ABSTRACCIÓN ===
Red Neuronal Convolucional ejecutó entrenamiento Forward/Backward propagation.

===== MÉTRICAS DEL MODELO =====
Nombre: Red Neuronal Convolucional
Precisión: 53.0%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.05
================================
Árbol de Fraudes calculó la ganancia de información (Entropía).

===== MÉTRICAS DEL MODELO =====
Nombre: Árbol de Fraudes
Precisión: 58.0%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.1
Profundidad máxima: 15
==============================
Regresión Logística minimizó el error cuadrático medio (MSE).

===== MÉTRICAS DEL MODELO =====
Nombre: Regresión Logística
Precisión: 51.5984%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.2
Coeficiente de regularización: 0.001
==============================

=== 2. AJUSTE DE PESOS (POLIMORFISMO INTERFACES) ===
RedNeuronal ajustó pesos usando backpropagation.
ArbolDecision optimizó divisiones del árbol.
ModeloRegresion aplicó regularización de crestas/Lasso.

=== 3. PIPELINE DE PROCESAMIENTO DE TEXTO ===
Tokenizador Básico:
[La, inteligencia, artificial, transforma, industrias]
Tokenizador HuggingFace:
[La, intel, igencia, artificial, transforma, industrias]

Tokenizador Básico:
[La, inteligencia, artificial, transforma, industrias]

Tokenizador HuggingFace:
[La, intel, igencia, artificial, transforma, industrias]
```
## Fase 6: Gestión Dinámica mediante el Framework de Colecciones

### Justificación del Diseño: Escalabilidad y Mantenibilidad
El uso de estructuras rígidas como los arreglos estáticos (`[]`) limita la escalabilidad del simulador, ya que obliga a definir un tamaño fijo de elementos en tiempo de compilación. En entornos de producción reales, los modelos de IA y las estrategias de procesamiento cambian dinámicamente en tiempo de ejecución. 

* **`List (ArrayList)`**: Aporta flexibilidad absoluta al inventario de modelos. Permite añadir, remover y manipular los algoritmos de IA en memoria sin preocuparse por desbordamientos de tamaño ni redimensionamientos manuales.
* **`Map (HashMap)`**: Centraliza las estrategias de tokenización en un catálogo indexado por claves de texto (`String`). Esto elimina por completo el uso de condicionales duros (`if-else` o `switch`) o variables sueltas, facilitando la mantenibilidad, ya que agregar un nuevo tokenizador al pipeline es tan simple como registrar una nueva combinación clave-valor.

### Evidencia de ejecución
```text
=== 1. ADMINISTRACIÓN DINÁMICA DE MODELOS (LIST) ===
Red Neuronal Convolucional ejecutó entrenamiento Forward/Backward propagation.

===== MÉTRICAS DEL MODELO =====
Nombre: Red Neuronal Convolucional
Precisión: 53.0%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.05
================================
Árbol de Fraudes calculó la ganancia de información (Entropía).

===== MÉTRICAS DEL MODELO =====
Nombre: Árbol de Fraudes
Precisión: 58.0%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.1
Profundidad máxima: 15
==============================
Regresión Logística minimizó el error cuadrático medio (MSE).

===== MÉTRICAS DEL MODELO =====
Nombre: Regresión Logística
Precisión: 51.5984%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.2
Coeficiente de regularización: 0.001
==============================
Red Neuronal Recurrente (LSTM) ejecutó entrenamiento Forward/Backward propagation.

===== MÉTRICAS DEL MODELO =====
Nombre: Red Neuronal Recurrente (LSTM)
Precisión: 52.88%
Épocas entrenadas: 1
Tasa de aprendizaje: 0.08
Capas ocultas: 3
==============================

=== 2. CATÁLOGO INDEXADO DE PROCESADORES (MAP) ===
Recuperando procesador 'BASICO':
[La, inteligencia, artificial, transforma, industrias]

Recuperando procesador 'HUGGING_FACE':
[La, intel, igencia, artificial, transforma, industrias]

=== 3. OPERACIONES AVANZADAS (FILTRADO POR REGLA DE NEGOCIO) ===
Filtrando modelos con precisión estrictamente mayor al 52.0%:
-> [APROBADO] Red Neuronal Convolucional | Precisión: 53.0%
-> [APROBADO] Árbol de Fraudes | Precisión: 58.0%
-> [APROBADO] Red Neuronal Recurrente (LSTM) | Precisión: 52.88%
Total de modelos que superaron el umbral: 3 de 4
```
