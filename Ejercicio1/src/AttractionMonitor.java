// Monitor para controlar el acceso a una atracción con capacidad limitada.
class AttractionMonitor {
    private int capacity;
    private int currentVisitors = 0;

    public AttractionMonitor(int capacity) {
        this.capacity = capacity;
    }

    // Permite la entrada si aún no se ha alcanzado la capacidad máxima.
    public synchronized void enterAttraction() throws InterruptedException {
        while (currentVisitors >= capacity) {
            wait();
        }
        currentVisitors++;
    }

    // Libera el espacio al finalizar la atracción.
    public synchronized void leaveAttraction() {
        currentVisitors--;
        notifyAll();
    }
}