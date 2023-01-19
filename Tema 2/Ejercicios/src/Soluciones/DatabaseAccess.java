package Soluciones;

import java.util.concurrent.Semaphore;

//objeto compartido
class BaseDatos{
    private long parada;
    private final int MAX_WRITERS = 50;
    private int datos = 0;
    // Semáforo binario para controlar el acceso a la base de datos por parte de los lectores
    private Semaphore readerSemaphore = new Semaphore(1);

    // Semáforo contador para controlar el acceso a la base de datos por parte de los escritores
    private Semaphore writerSemaphore = new Semaphore(MAX_WRITERS);

    // Semáforo para conseguir la exclusión mutua en el procedimiento del objeto compartido
    private Semaphore mutex = new Semaphore(1);

    //metodo que simula la lectura de la base de datos
    public void readFromDatabase(int i, int datos){
        System.out.println("Usuario" + i + ": leyendo de la base de datos. Total de datos almacenados: "+datos);

    }

    //metodo que simula la escritura de la base de datos
    public void writeToDatabase(int i,int datos){
        System.out.println("Usuario" + i + ": escribiendo de la base de datos.Total de datos almacenados: "+datos);
    }
    // Método para que un lector acceda a la base de datos
    public void read(int i) {
        try {
            // Si el semáforo binario está bloqueado, bloquearse hasta que se libere
            readerSemaphore.acquire();
            // Una vez desbloqueado, acceder a la base de datos
            mutex.acquire();
            datos--;
            readFromDatabase(i,datos);
            mutex.release();
            parada = (long) (Math.random() * (1000 - 200) + 200);
            Thread.sleep(parada);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            // Liberar el semáforo binario para permitir que otros lectores accedan a la base de datos
            writerSemaphore.release();
        }
    }

    // Método para que un escritor acceda a la base de datos
    public void write(int i) {
        try {
            // bloquearse hasta que se libere
            writerSemaphore.acquire();
            mutex.acquire();
            // Escribir en la base de datos
            datos++;
            writeToDatabase(i,datos);
            mutex.release();
        } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
        } finally {
            // Liberar el semáforo binario
            readerSemaphore.release();

        }
    }
}

class readerDB extends Thread{
    private BaseDatos bd = new BaseDatos();
    int id;

    public readerDB(BaseDatos bd, int id) {
        this.bd = bd;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            bd.read(this.id);
        }
    }
}

class writerDB extends Thread{
    private BaseDatos bd = new BaseDatos();
    int id;

    public writerDB(BaseDatos bd, int id) {
        this.bd = bd;
        this.id = id;
    }

    @Override
    public void run(){
        while (true){
            bd.write(this.id);
        }
    }
}

public class DatabaseAccess {
    public static void main(String[] args) {
        BaseDatos bd = new BaseDatos();
        final int READERS = 25;
        final int WRITERS = 5;

        for (int i= 1; i<WRITERS; i++){
            Thread hWrite = new writerDB(bd,i);
            hWrite.start();
        }
        for (int i= 1; i<READERS; i++){
            Thread hRead = new readerDB(bd,i);
            hRead.start();
        }
    }
}







