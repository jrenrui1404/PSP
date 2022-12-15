package ProductoConsumudor;

import static java.lang.Thread.sleep;

class ContenedorSyn<T> {
    private T dato;
    synchronized boolean datoDisponible() {
        return (this.dato != null);
    }

    public T getValor() throws InterruptedException{
        synchronized (this){
            while (!datoDisponible()){
                this.wait();
            }
            T v = this.getValor();
            this.setValor(null);
            this.notifyAll();
            return v;
        }
    }

    public void setValor(T v) throws InterruptedException{
        synchronized (this){
            while (datoDisponible()){
                this.wait();
            }
            this.setValor(v);
            this.notifyAll();
        }
    }
}


    class HiloProductorSyn implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloProductorSyn(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        for (int i = 1;; i++) {
            System.out.printf("Hilo %s produce valor %s.\n", this.miNombre, i);
            this.cont.put(i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class HiloConsumidorSyn implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloConsumidorSyn(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        while (true) {
            Integer dato = this.cont.get();
            System.out.printf("Hilo %s consume valor %d.\n", this.miNombre, dato);
            }
        }
    }



public class SynchronizedActividad213 {

    public static void main(String[] args) {
        Contenedor<Integer> almacen = new Contenedor<Integer>();
        Thread hprod = new Thread(new HiloProductor(almacen, "P"));
        Thread hcons = new Thread(new HiloConsumidor(almacen, "C"));
        hprod.start();
        hcons.start();
    }

}