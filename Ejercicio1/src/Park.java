import java.util.concurrent.atomic.AtomicInteger;

// Clase que representa el parque temático.
// Contiene el monitor para las puertas, los monitores para las atracciones y un generador de códigos únicos para visitantes.
class Park {
    private DoorMonitor doorMonitor;
    private AttractionMonitor attraction1;
    private AttractionMonitor attraction2;
    private AtomicInteger visitorCodeGenerator;

    public Park(int numDoors, int attraction1Capacity, int attraction2Capacity) {
        this.doorMonitor = new DoorMonitor(numDoors);
        this.attraction1 = new AttractionMonitor(attraction1Capacity);
        this.attraction2 = new AttractionMonitor(attraction2Capacity);
        this.visitorCodeGenerator = new AtomicInteger(1);
    }

    // Asigna y devuelve un código único correlativo.
    public int registerVisitor() {
        return visitorCodeGenerator.getAndIncrement();
    }

    public DoorMonitor getDoorMonitor() {
        return doorMonitor;
    }

    public AttractionMonitor getAttraction1() {
        return attraction1;
    }

    public AttractionMonitor getAttraction2() {
        return attraction2;
    }
}