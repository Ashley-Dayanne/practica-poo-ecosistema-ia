package com.ia.aplicacion;

import com.ia.interfaces.Tokenizador;
import com.ia.interfaces.TokenizadorBasico;
import com.ia.interfaces.TokenizadorHuggingFace;
import com.ia.modelos.ArbolDecision;
import com.ia.modelos.ModeloIA;
import com.ia.modelos.ModeloRegresion;
import com.ia.modelos.RedNeuronal;

// Importaciones explícitas del framework de colecciones de Java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorIA {

    public static void main(String[] args) {

        System.out.println("=== 1. ADMINISTRACIÓN DINÁMICA DE MODELOS (LIST) ===");

        // Declaración e instanciación de la colección genérica List
        List<ModeloIA> inventarioModelos = new ArrayList<>();

        // Agregamos componentes dinámicamente sin preocuparnos por un tamaño fijo
        inventarioModelos.add(new RedNeuronal("Red Neuronal Convolucional", 0.05, 5));
        inventarioModelos.add(new ArbolDecision("Árbol de Fraudes", 0.1, 15));
        inventarioModelos.add(new ModeloRegresion("Regresión Logística", 0.2, 0.001));

        // Añadimos un modelo extra para demostrar el crecimiento dinámico de la lista
        inventarioModelos.add(new RedNeuronal("Red Neuronal Recurrente (LSTM)", 0.08, 3));

        // Lazo polimórfico mediante un ciclo for-each para entrenar y mostrar métricas
        for (ModeloIA modelo : inventarioModelos) {
            modelo.entrenar();
            modelo.mostrarMetricas();
        }

        System.out.println("\n=== 2. CATÁLOGO INDEXADO DE PROCESADORES (MAP) ===");

        // Creación del mapa para centralizar y registrar los tokenizadores
        Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();

        // Registro de los componentes asociándolos a una clave semántica única
        catalogoTokenizadores.put("BASICO", new TokenizadorBasico());
        catalogoTokenizadores.put("HUGGING_FACE", new TokenizadorHuggingFace());

        String textoPrueba = "La inteligencia artificial transforma industrias";

        // Recuperación exitosa del tokenizador desde el mapa mediante su clave
        System.out.println("Recuperando procesador 'BASICO':");
        Tokenizador tokBasico = catalogoTokenizadores.get("BASICO");
        System.out.println(Arrays.toString(tokBasico.dividirTexto(textoPrueba)));

        System.out.println("\nRecuperando procesador 'HUGGING_FACE':");
        Tokenizador tokHF = catalogoTokenizadores.get("HUGGING_FACE");
        System.out.println(Arrays.toString(tokHF.dividirTexto(textoPrueba)));


        System.out.println("\n=== 3. OPERACIONES AVANZADAS (FILTRADO POR REGLA DE NEGOCIO) ===");
        
        // Umbral específico de precisión para el filtrado
        double umbralPrecision = 52.0;
        System.out.println("Filtrando modelos con precisión estrictamente mayor al " + umbralPrecision + "%:");
        
        int modelosAprobados = 0;
        for (ModeloIA modelo : inventarioModelos) {
            if (modelo.getPrecision() > umbralPrecision) {
                System.out.println("-> [APROBADO] " + modelo.getNombre() + " | Precisión: " + modelo.getPrecision() + "%");
                modelosAprobados++;
            }
        }
        
        System.out.println("Total de modelos que superaron el umbral: " + modelosAprobados + " de " + inventarioModelos.size());
    }
}
