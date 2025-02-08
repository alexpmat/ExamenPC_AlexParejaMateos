import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Clase que representa el parque temático
class ParqueTematico {
    // Semáforo para controlar el acceso por las puertas (número de puertas disponibles)
    private final Semaphore doorSemaphore;
    // Generador de códigos únicos para cada visitante
    private final AtomicInteger codigoGenerator = new AtomicInteger(1);

    // Semáforos para controlar el acceso a dos atracciones (cada uno con capacidad limitada)
    private final Semaphore attraction1;
    private final Semaphore attraction2;

    public ParqueTematico(int numPuertas, int capAttraction1, int capAttraction2) {
        this.doorSemaphore = new Semaphore(numPuertas);
        this.attraction1 = new Semaphore(capAttraction1);
        this.attraction2 = new Semaphore(capAttraction2);
    }

    // Registra al visitante y asigna un código único correlativo
    public int registrarVisitante() {
        return codigoGenerator.getAndIncrement();
    }

    public Semaphore getDoorSemaphore() {
        return doorSemaphore;
    }

    public Semaphore getAttraction1() {
        return attraction1;
    }

    public Semaphore getAttraction2() {
        return attraction2;
    }
}

// Clase que simula a un visitante del parque
class Visitor implements Runnable {
    private final ParqueTematico parque;
    private final int codigo;

    public Visitor(ParqueTematico parque) {
        this.parque = parque;
        this.codigo = parque.registrarVisitante();
    }

    public Visitor(Park park, int codigo) {
        this.codigo = codigo;
        this.parque = new ParqueTematico(3, 2, 2);
    }

    public Visitor(Park park) {
        this(park, park.registerVisitor());
    }

    @Override
    public void run() {
        try {
            // Simula la llegada al parque y registro
            System.out.println("Visitante " + codigo + " llega al parque.");

            // Acceso al parque: esperar a que alguna puerta (permiso) esté disponible
            parque.getDoorSemaphore().acquire();
            System.out.println("Visitante " + codigo + " está entrando por una puerta.");
            Thread.sleep(500); // Simula el tiempo de acceso
            System.out.println("Visitante " + codigo + " ha ingresado al parque.");
            // Se libera la puerta para el siguiente visitante en la cola
            parque.getDoorSemaphore().release();

            // Acceso a la Atracción 1
            parque.getAttraction1().acquire();
            System.out.println("Visitante " + codigo + " accede a la Atracción 1.");
            Thread.sleep(1000); // Simula la duración de la atracción
            System.out.println("Visitante " + codigo + " termina la Atracción 1.");
            parque.getAttraction1().release();

            // Acceso a la Atracción 2
            parque.getAttraction2().acquire();
            System.out.println("Visitante " + codigo + " accede a la Atracción 2.");
            Thread.sleep(1000);
            System.out.println("Visitante " + codigo + " termina la Atracción 2.");
            parque.getAttraction2().release();

        } catch (InterruptedException e) {
            System.err.println("Visitante " + codigo + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}