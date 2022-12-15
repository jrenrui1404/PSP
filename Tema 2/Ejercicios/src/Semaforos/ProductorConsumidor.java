package Semaforos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

class Almacen {

    private final int MAX_LIMITE = 20;
    private int producto = 0;

    private Semaphore productor = new Semaphore(MAX_LIMITE);
    private Semaphore consumidor = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void producir(String nombreProductor) {
        System.out.println(nombreProductor + " intentando alamcenar un producto");
        try {
            productor.acquire();
            System.out.println(nombreProductor + " almacena un producto. ");

            mutex.acquire();
            producto++;

            mutex.release();
            Thread.sleep(500);

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null );
        }
    }

}


public class ProductorConsumidor {

    public static void main(String[] args) {
        final int PRODUCTOR = 3;
        final int CONSUMIDOR = 10;
    }

}
