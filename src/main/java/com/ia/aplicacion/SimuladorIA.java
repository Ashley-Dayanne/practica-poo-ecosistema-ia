package com.ia.aplicacion;

import com.ia.dto.PromptInput; // Importamos el nuevo Record
import com.ia.exceptions.IAComponentException;
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

        System.out.println("=== FASE 8: MODERNIZACIÓN JAVA 17 & CALIDAD ===");

        List<ModeloIA> inventarioModelos = new ArrayList<>();
        Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

        catalogoTokenizadores.put("BASICO", new TokenizadorBasico());
        catalogoTokenizadores.put("HUGGING_FACE", new TokenizadorHuggingFace());

        // 1. USO DEL RECORD (Inmutabilidad del flujo de texto)
        // Reemplazamos las variables sueltas por un registro estructurado
        PromptInput entradaIA = new PromptInput(
            "Actúa como un analista senior de datos",
            "La inteligencia artificial transforma industrias de manera exponencial"
        );

        System.out.println("\n[Datos del Prompt alimentados de forma inmutable]:");
        // El método toString() se genera automáticamente gracias al Record
        System.out.println(entradaIA); 

        // 2. USO DEL SWITCH MEJORADO DE JAVA 17
        // Llamamos a nuestra función moderna de selección
        try {
            String tipoBuscado = "HUGGING_FACE";
            System.out.println("\nSeleccionando estrategia usando Switch Expression para: " + tipoBuscado);
            Tokenizador miTokenizador = obtenerTokenizadorEstrategico(tipoBuscado, catalogoTokenizadores);
            
            // Extraemos la consulta usando el getter del Record (entradaIA.userQuery())
            String[] tokens = miTokenizador.dividirTexto(entradaIA.userQuery());
            System.out.println("Resultado de la Tokenización: " + Arrays.toString(tokens));

        } catch (IAComponentException e) {
            System.err.println("[FALLO CONTROLADO]: " + e.getMessage());
        }

        // 3. FLUJO DE EXCEPCIÓN INTEGRAL CON FINALLY
        try {
            System.out.println("\n[Provocando fallo controlado buscando un tokenizador obsoleto 'NLTK'...]");
            Tokenizador tokInvalido = obtenerTokenizadorEstrategico("NLTK", catalogoTokenizadores);
            System.out.println(Arrays.toString(tokInvalido.dividirTexto("Test")));
        } catch (IAComponentException e) {
            System.err.println("\n[REPORTANDO FALLO EN TABLERO]: " + e.getMessage());
        } finally {
            System.out.println("\n=======================================================");
            System.out.println("QUALITY INSPECTION: Flujo verificado bajo estándares Java 17.");
            System.out.println("=======================================================");
        }
    }

    /**
     * Expresión Switch de Java 17: Retorna valores directamente, elimina el boilerplate 
     * de los 'break' y provee un control de errores robusto.
     */
    private static Tokenizador obtenerTokenizadorEstrategico(String tipo, Map<String, Tokenizador> catalogo) {
        Tokenizador tokenizador = switch (tipo.toUpperCase()) {
            case "BASICO" -> catalogo.get("BASICO");
            case "HUGGING_FACE" -> catalogo.get("HUGGING_FACE");
            default -> throw new IAComponentException("Error: La estrategia '" + tipo + "' no se encuentra soportada en el ecosistema.");
        };

        // Doble verificación recomendada por SonarQube para evitar NullPointerExceptions accidentales
        if (tokenizador == null) {
            throw new IAComponentException("Error: El componente registrado para '" + tipo + "' está corrupto o vacío.");
        }
        
        return tokenizador;
    }
}
