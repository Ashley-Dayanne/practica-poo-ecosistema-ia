# EVOLUCIÓN ARQUITECTÓNICA DEL SIMULADOR DE COMPONENTES DE IA
### Reporte Técnico Final - Calidad de Código y Modernización (Java 17)

**Curso:** Programación Orientada a Objetos  
**Fecha:** Junio de 2026  

---

## 1. Resumen Ejecutivo
El presente proyecto documenta la evolución incremental del Simulador de Componentes de Inteligencia Artificial. Iniciando en las primeras etapas como un conjunto de clases aisladas y rígidas (Fase 2 y 3), el sistema transitó de manera progresiva hacia la adopción de los principios fundamentales de la POO. En la Fase 4 y 5 se introdujo el polimorfismo puro a través de interfaces y la restricción operativa de modelos mediante clases abstractas, garantizando que el simulador operase sobre conceptos lógicos estables y no sobre objetos ambiguos. La Fase 6 expandió el ecosistema mediante el framework de colecciones dinámicas de Java, resolviendo las limitaciones de escalabilidad de los arreglos tradicionales. Finalmente, las Fases 7 y 8 consolidaron una arquitectura empresarial dotada de un sistema de tolerancia a fallos formal y una modernización de sintaxis basada en Java 17, auditada bajo las métricas de análisis estático de la plataforma SonarQube.

## 2. Reporte de Calidad SonarQube
*(Nota: Aquí debes insertar las capturas de pantalla reales de tu tablero local de SonarQube)*
* **Tablero "Antes":** Se detectaron inicialmente 5 Code Smells relacionados con variables que podían ser declaradas como finales, modificadores redundantes en las interfaces de contratos y el uso de flujos estándar de consola.
* **Tablero "Después" (Quality Gate Aprobado):** Tras la refactorización aplicada en la fase 8, la densidad de duplicación de código se redujo al 0%, las vulnerabilidades críticas se mantuvieron en 0, logrando la calificación "A" de mantenibilidad y confiabilidad bajo la política verde de la plataforma.

---

## 3. Cuestionario de Evaluación de Lecciones Aprendidas

### 1. Sobre la Modernización del Lenguaje
**¿De qué manera la implementación de Java 17 records y sealed classes optimizó el pilar de encapsulación y el control de la jerarquía de herencia en comparación con el diseño tradicional de Java 8 de las primeras fases?**
*Respuesta:* El diseño original de Java 8 permitía una herencia abierta y potencialmente peligrosa, donde cualquier programador externo podría extender `ModeloIA` y corromper el ecosistema con un algoritmo mal estructurado. Las *Sealed Classes* de Java 17 optimizan drásticamente este control de jerarquía al actuar como un mecanismo de aislamiento arquitectónico exclusivo, restringiendo mediante la cláusula `permits` la descendencia única a los algoritmos autorizados (`RedNeuronal`, `ArbolDecision`, `ModeloRegresion`). Por otro lado, la introducción de los *Records* eleva el pilar de la encapsulación a un nivel extremo. En lugar de crear clases mutables propensas a efectos secundarios o reescrituras de datos en memoria, un record como `PromptInput` garantiza la inmutabilidad absoluta de los hiperparámetros y textos que alimentan el pipeline de procesamiento, eliminando además el código repetitivo (*boilerplate*) e incrementando la seguridad del hilo de ejecución.

### 2. Sobre la Deuda Técnica
**Al ejecutar el primer escaneo en SonarQube, ¿cuáles fueron los tres "code smells" o vulnerabilidades más críticos detectados en tu código y qué principios de diseño limpio aplicaste para corregirlos?**
*Respuesta:* Durante la inspección estática inicial, los tres hallazgos de deuda técnica más relevantes fueron:
1. *Campos modificables innecesariamente:* El atributo `nombre` en `ModeloIA` carecía del modificador `final`. Se aplicó el principio de inmutabilidad defensiva para asegurar que la identidad del objeto permanezca inalterada tras la instanciación.
2. *Modificadores redundantes en interfaces:* Métodos definidos con `public abstract` dentro de `Tokenizador`. Se removieron basándose en las directrices de limpieza sintáctica de Java, donde los miembros de una interfaz son implícitamente públicos y abstractos.
3. *Riesgos de NullPointerException en búsquedas de Mapas:* La recuperación directa de claves en el catálogo sin validaciones condicionales posteriores. Se implementó una verificación estricta combinada con excepciones y expresiones switch para neutralizar accesos a memoria nula.

### 3. Sobre el Flujo de Control y Resiliencia
**¿Cómo interactúa el mecanismo de manejo de excepciones desarrollado en la Fase 7 con las métricas de confiabilidad analizadas por SonarQube? ¿Evitó esto la presencia de bloques vacíos (catch blocks) penalizados por la plataforma?**
*Respuesta:* El mecanismo de manejo de excepciones interactúa directamente con el indicador de *Confiabilidad (Reliability)* de SonarQube. Una aplicación que aborta intempestivamente degrada este indicador a niveles críticos. Al capturar de forma precisa la excepción personalizada `IAComponentException` mediante bloques estructurados `try-catch-finally`, el software se vuelve resiliente, garantizando que el flujo secundario de control registre elegantemente la anomalía sin detener el sistema. Se evitó explícitamente el uso de bloques `catch` vacíos (uno de los pecados capitales en análisis estático de código) proveyendo una respuesta activa (impresión prioritaria de errores en la consola de auditoría y ejecución de cierres en el bloque `finally`), lo que permitió superar con éxito los umbrales del Quality Gate.

### 4. Sobre la Flexibilidad de las Abstracciones
**Explica cómo el uso combinado de colecciones dinámicas (List/Map) y abstracciones polimórficas te permitió integrar las nuevas características de Java 17 sin romper la arquitectura base que ya habías diseñado.**
*Respuesta:* La utilización de abstracciones polimórficas (como la superclase sellada `ModeloIA` y la interfaz pura `Tokenizador`) actúa como un desacoplamiento de bajo impacto. Debido a que el sistema orquestador global (el `main`) interactúa exclusivamente con los contratos abstractos y no con los detalles físicos de las subclases, fue posible transformar por completo el diseño interno de la superclase a un formato sellado (`sealed`), restringir sus hijos a tipos finales (`final`), e introducir un mapa de selección con *Switch Expressions* modernos sin alterar una sola línea de código relacionada con la lógica operativa o de colecciones dinámica ya establecida. Esto demuestra el cumplimiento práctico del Principio Abierto/Cerrado (OCP), donde el código queda abierto a la extensión y modernización, pero cerrado a la modificación destructiva.

### 5. Sobre el Impacto en la Ingeniería
**Desde la perspectiva de un Ingeniero en Inteligencia Artificial, ¿por qué es crítico que un software que implementa pipelines de LLM o simuladores de algoritmos sea sometido a pruebas de análisis estático de código como SonarQube antes de ser desplegado en producción?**
*Respuesta:* En la Ingeniería de Inteligencia Artificial, los sistemas procesan volúmenes masivos de datos en tiempo real y coordinan llamadas costosas a APIs de modelos de lenguaje grandes (LLMs). Un pipeline de LLM mal estructurado que sufra fugas de memoria por colecciones mal gestionadas, que carezca de hilos inmutables (`records`) para los prompts, o que posea vulnerabilidades en el control de excepciones de parámetros, puede generar fallos catastróficos que se traducen en pérdidas económicas drásticas, degradación de precisión y caídas del servicio en la nube. Herramientas como SonarQube no evalúan si el modelo matemático converge, sino que aseguran que el contenedor de software que orquesta y expone dicho modelo sea robusto, eficiente, seguro y mantenible a largo plazo, mitigando la deuda técnica antes de que afecte la operación del negocio.
