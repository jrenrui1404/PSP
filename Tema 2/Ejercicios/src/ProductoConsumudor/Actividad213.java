package ProductoConsumudor;

class Contenedor213<T> {
    private T dato;

    synchronized public T get() {
        T result = this.dato;
        this.dato = null;
        return result;
    }

    synchronized public void put(T valor) {
        this.dato = valor;
    }

    synchronized boolean datoDisponible() {
        return (this.dato != null);
    }
}

class HiloProductor213 implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloProductor213(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        for (int i = 1;; i++) {
            synchronized (this.cont) {
                while (this.cont.datoDisponible()) {
                    try {
                        this.cont.wait();
                    } catch (InterruptedException ex) {
                    }
                }
                System.out.printf("Hilo %s produce valor %s.\n", this.miNombre, i);
                this.cont.put(i);
                this.cont.notifyAll();
            }
        }
    }

}

class HiloConsumidor213 implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloConsumidor213(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                while (!this.cont.datoDisponible()) {
                    try {
                        this.cont.wait();
                    } catch (InterruptedException ex) {
                    }
                }
                Integer dato = this.cont.get();
                this.cont.notifyAll();
                System.out.printf("Hilo %s consume valor %d.\n", this.miNombre, dato);
            }
        }
    }

}

public class Actividad213 {

    public static void main(String[] args) {
        Contenedor<Integer> almacen = new Contenedor<Integer>();
        Thread hprod = new Thread(new HiloProductor(almacen, "P"));
        Thread hcons = new Thread(new HiloConsumidor(almacen, "C"));
        hprod.start();
        hcons.start();
    }

}