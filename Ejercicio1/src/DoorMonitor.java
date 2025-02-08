import java.util.concurrent.atomic.AtomicInteger;

// Monitor para controlar el acceso a las puertas del parque.
class DoorMonitor {
    private int availableDoors;

    public DoorMonitor(int availableDoors) {
        this.availableDoors = availableDoors;
    }

    // Método sincronizado que permite a un visitante acceder a una puerta.
    public synchronized void enterDoor() throws InterruptedException {
        // Mientras no haya puertas libres, el hilo espera.
        while (availableDoors <= 0) {
            wait();
        }
        availableDoors--;
    }

    // Método sincronizado para liberar una puerta una vez que el visitante ha entrado.
    public synchronized void leaveDoor() {
        availableDoors++;
        notifyAll(); // Notifica a los hilos que esperan que ahora hay una puerta disponible.
    }
}