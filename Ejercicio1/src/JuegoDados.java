import java.util.*;
import java.util.concurrent.CountDownLatch;

public class JuegoDados {

    public int[] tiradaDados(int numDados) {
        // Arrays para almacenar los resultados y las condiciones de cada dado.
        int[] diceResults = new int[numDados];
        String[] diceConditions = new String[numDados];

        // Latch para sincronizar la finalización de todos los hilos.
        CountDownLatch latch = new CountDownLatch(numDados);

        // Crear y lanzar un hilo por cada dado.
        for (int i = 0; i < numDados; i++) {
            final int index = i;  // Capturamos el índice para cada hilo.
            new Thread(() -> {
                try {
                    // Simulación del lanzamiento del dado
                    Random rand = new Random();
                    int baseResult = rand.nextInt(6) + 1; // Valor entre 1 y 6

                    // Selección aleatoria de una condición especial
                    String[] conditionsArray = {"Normal", "Viento fuerte", "Superficie irregular", "Condición ideal"};
                    String condition = conditionsArray[rand.nextInt(conditionsArray.length)];

                    // Modificar el resultado según la condición
                    int finalResult = baseResult;
                    if ("Viento fuerte".equals(condition)) {
                        finalResult = baseResult - 1;
                    } else if ("Superficie irregular".equals(condition)) {
                        finalResult = baseResult + 1;
                    }
                    // Asegurar que el resultado se mantenga en [1,6]
                    finalResult = Math.max(1, Math.min(6, finalResult));

                    // Guardar resultado y condición
                    diceResults[index] = finalResult;
                    diceConditions[index] = condition;

                    // Mostrar resultado intermedio (interfaz de usuario sencilla)
                    System.out.println("Dado " + (index + 1) + ": Base=" + baseResult +
                            ", Condición=" + condition + " => Resultado final=" + finalResult);
                } catch(Exception e) {
                    System.err.println("Error en el dado " + (index + 1) + ": " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        // Esperar a que todos los hilos hayan terminado
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo principal fue interrumpido: " + e.getMessage());
        }

        // Análisis global de los resultados
        double suma = 0;
        int[] frecuencia = new int[7]; // Índices 1..6
        for (int result : diceResults) {
            suma += result;
            frecuencia[result]++;
        }
        double promedio = suma / numDados;

        // Determinar el valor más frecuente
        int masFrecuente = 1;
        for (int j = 2; j <= 6; j++) {
            if (frecuencia[j] > frecuencia[masFrecuente]) {
                masFrecuente = j;
            }
        }

        // Calcular la desviación estándar
        double varianza = 0;
        for (int result : diceResults) {
            varianza += Math.pow(result - promedio, 2);
        }
        varianza /= numDados;
        double desviacionEstandar = Math.sqrt(varianza);

        // Mostrar estadísticas globales
        System.out.println("\n--- Análisis de Resultados ---");
        System.out.println("Promedio: " + promedio);
        System.out.println("Valor más frecuente: " + masFrecuente);
        System.out.println("Desviación estándar: " + desviacionEstandar);

        // Análisis de correlación entre condiciones especiales y resultados:
        // Se agrupan los resultados según la condición aplicada.
        Map<String, List<Integer>> condicionesMap = new HashMap<>();
        for (int i = 0; i < numDados; i++) {
            condicionesMap.computeIfAbsent(diceConditions[i], k -> new ArrayList<>()).add(diceResults[i]);
        }
        System.out.println("\nCorrelación entre condiciones y resultados:");
        for (Map.Entry<String, List<Integer>> entry : condicionesMap.entrySet()) {
            List<Integer> resultados = entry.getValue();
            double promCondicion = resultados.stream().mapToInt(Integer::intValue).average().orElse(0);
            System.out.println("Condición: " + entry.getKey() +
                    " | Lanzamientos: " + resultados.size() +
                    " | Promedio: " + promCondicion);
        }

        return diceResults;
    }

    // Método main para probar la función (simula una interfaz de usuario básica)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de dados a lanzar: ");
        int numDados = scanner.nextInt();

        JuegoDados juego = new JuegoDados();
        int[] resultados = juego.tiradaDados(numDados);

        System.out.println("\nResultados finales:");
        System.out.println(Arrays.toString(resultados));
    }
}