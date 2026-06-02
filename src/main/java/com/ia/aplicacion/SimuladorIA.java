package com.ia.aplicacion;

import com.ia.exceptions.IAComponentException; // Importamos la excepción
import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;
import com.ia.modelos.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorIA {

    public static void main(String[] args) {

        System.out.println("=== FASE 7: CONTROL DE ERRORES Y ROBUSTEZ ===");

        // Creamos el inventario básico
        List<ModeloIA> inventarioModelos = new ArrayList<>();
        Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

        catalogoTokenizadores.put("BASICO", new TokenizadorBasico());
        catalogoTokenizadores.put("HUGGING_FACE", new TokenizadorHuggingFace());

        // -----------------------------------------------------------------
        // PRUEBA DE ROBUSTEZ 1: Error en Tasa de Aprendizaje (try-catch)
        // -----------------------------------------------------------------
        try {
            System.out.println("\n[Intentando crear una Red Neuronal válida...]");
            RedNeuronal redValida = new RedNeuronal("Red Convolucional", 0.05, 5);
            inventarioModelos.add(redValida);
            System.out.println("-> Modelo añadido con éxito.");

            System.out.println("\n[Intentando inyectar una tasa de aprendizaje inválida (1.5)...]");
            // Esto lanzará nuestra IAComponentException de forma inmediata
            redValida.setTasaAprendizaje(1.5); 

            // Esta línea no se ejecutará debido al salto al bloque catch
            System.out.println("Esta línea nunca se imprimirá.");

        } catch (IAComponentException e) {
            System.err.println("\n[REPORTANDO FALLO CONTROLADO]: " + e.getMessage());
        }

        // -----------------------------------------------------------------
        // PRUEBA DE ROBUSTEZ 2: Error al buscar en el catálogo de Mapas
        // -----------------------------------------------------------------
        try {
            System.out.println("\n[Intentando recuperar un tokenizador inexistente 'OPEN_AI'...]");
            String claveBuscada = "OPEN_AI";
            Tokenizador tokenizadorInexistente = catalogoTokenizadores.get(claveBuscada);

            // Validación requerida por el enunciado: si devuelve null, lanzamos excepción
            if (tokenizadorInexistente == null) {
                throw new IAComponentException("Error: El componente Tokenizador con la clave '" + claveBuscada + "' no está registrado en el catálogo del sistema.");
            }

            // No se ejecutará
            System.out.println(Arrays.toString(tokenizadorInexistente.dividirTexto("Hola")));

        } catch (IAComponentException e) {
            System.err.println("\n[REPORTANDO FALLO CONTROLADO]: " + e.getMessage());
        } 
        
        // -----------------------------------------------------------------
        // BLOQUE FINALLY: Garantiza la auditoría del sistema
        // -----------------------------------------------------------------
        finally {
            System.out.println("\n=======================================================");
            System.out.println("AUDITORÍA DE LOGS: La fase de limpieza y verificación del simulador ha concluido.");
            System.out.println("El sistema se mantiene íntegro y tolerante a fallos.");
            System.out.println("=======================================================");
        }

        // Demostramos que el hilo principal del programa NO se detuvo y puede continuar
        System.out.println("\nEl programa continuó de forma segura después de manejar los errores.");
        System.out.println("Modelos estables en inventario: " + inventarioModelos.size());
    }
}
