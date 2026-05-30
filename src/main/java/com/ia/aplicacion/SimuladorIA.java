package com.ia.aplicacion;
import com.ia.modelos.ModeloIA;

public class SimuladorIA {

    public static void main(String[] args) {

        // Crear objetos
        ModeloIA redNeuronal = new ModeloIA("RedNeuronal", 0.2);
        ModeloIA arbolDecision = new ModeloIA("ArbolDecision", 0.15);

        // Mostrar estado inicial
        redNeuronal.mostrarMetricas();
        arbolDecision.mostrarMetricas();

        // Intentar asignar valor inválido
        System.out.println("\nIntentando asignar tasa inválida...");
        redNeuronal.setTasaAprendizaje(-0.5);

        // Entrenamiento
        System.out.println("\n=== INICIANDO ENTRENAMIENTO ===");

        for (int i = 1; i <= 5; i++) {

            System.out.println("\nEntrenamiento #" + i);

            redNeuronal.entrenar();
            arbolDecision.entrenar();
        }

        // Mostrar resultados finales
        System.out.println("\n=== RESULTADOS FINALES ===");

        redNeuronal.mostrarMetricas();
        arbolDecision.mostrarMetricas();
    }
}
