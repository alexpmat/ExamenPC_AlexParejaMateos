// Clase principal que lanza la simulación.
public class ParkMonitorSimulation {
    public static void main(String[] args) {
        // Configuración del parque:
        // - 3 puertas disponibles para el ingreso.
        // - Cada atracción admite 2 visitantes simultáneamente.
        Park park = new Park(3, 2, 2);

        int numVisitors = 10;  // Número total de visitantes a simular.
        Thread[] visitorThreads = new Thread[numVisitors];

        // Se crean y se inician los hilos de visitantes.
        for (int i = 0; i < numVisitors; i++) {
            visitorThreads[i] = new Thread(new Visitor(park));
            visitorThreads[i].start();
            try {
                // Se simula la llegada escalonada de visitantes.
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Se espera a que todos los hilos finalicen.
        for (int i = 0; i < numVisitors; i++) {
            try {
                visitorThreads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Simulación del parque temático completada.");
    }
}