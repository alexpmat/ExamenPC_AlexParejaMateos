import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Clase principal para ejecutar la simulación
public class SimulacionSemaforos {
    public static void main(String[] args) {
        // Configuración del parque:
        // - 3 puertas de acceso.
        // - Cada atracción permite 2 visitantes simultáneamente.
        ParqueTematico parque = new ParqueTematico(3, 2, 2);
        int numVisitantes = 10;  // Número total de visitantes a simular

        // Se utiliza un ExecutorService para gestionar los hilos de visitantes.
        ExecutorService executor = Executors.newFixedThreadPool(numVisitantes);

        // Crear y enviar las tareas de visitantes al executor
        for (int i = 0; i < numVisitantes; i++) {
            executor.execute(new Visitor(parque));
            try {
                // Simula la llegada escalonada de visitantes
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }
}