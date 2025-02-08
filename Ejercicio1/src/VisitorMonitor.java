// Clase que simula a un visitante que accede al parque y a sus atracciones.
class VisitorMonitor implements Runnable {
    private Park park;
    private int visitorCode;

    public VisitorMonitor(Park park) {
        this.park = park;
        // Se registra el visitante y se le asigna un código único.
        this.visitorCode = park.registerVisitor();
    }

    @Override
    public void run() {
        try {
            System.out.println("Visitante " + visitorCode + " ha llegado al parque.");

            // Acceso a la entrada del parque: espera hasta que una puerta esté libre.
            park.getDoorMonitor().enterDoor();
            System.out.println("Visitante " + visitorCode + " está entrando por una puerta.");
            Thread.sleep(500); // Simula el tiempo para atravesar la puerta.
            park.getDoorMonitor().leaveDoor();
            System.out.println("Visitante " + visitorCode + " ha ingresado al parque.");

            // Acceso a la Atracción 1.
            park.getAttraction1().enterAttraction();
            System.out.println("Visitante " + visitorCode + " accede a la Atracción 1.");
            Thread.sleep(1000); // Simula la duración de la atracción.
            park.getAttraction1().leaveAttraction();
            System.out.println("Visitante " + visitorCode + " ha finalizado la Atracción 1.");

            // Acceso a la Atracción 2.
            park.getAttraction2().enterAttraction();
            System.out.println("Visitante " + visitorCode + " accede a la Atracción 2.");
            Thread.sleep(1000);
            park.getAttraction2().leaveAttraction();
            System.out.println("Visitante " + visitorCode + " ha finalizado la Atracción 2.");

        } catch (InterruptedException e) {
            System.err.println("Visitante " + visitorCode + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}