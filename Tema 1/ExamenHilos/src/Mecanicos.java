class Interbloqueos{
    private Object herramienta1 = new Object();
    private Object herramienta2 = new Object();

    //metodos
    public void quieroLaLLave(){
        try{
            synchronized (herramienta1){
                Thread.sleep(2000); //le dejo un tiempo
                synchronized (herramienta2){
                    Thread.sleep(2000); //le dejo un tiempo
                }
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void yoQuieroLaLLave(){
        try{
            synchronized (herramienta2){
                Thread.sleep(2000); //le dejo un tiempo
                synchronized (herramienta1){
                    Thread.sleep(2000); //le dejo un tiempo
                }
            }

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

} //fin class Interbloqueo


class Hilo implements Runnable{
    private Interbloqueos mecaBloq;
    private int herramienta;

    public Hilo(Interbloqueos mecaBloq, int herramienta){
        this.mecaBloq = mecaBloq;
        this.herramienta = herramienta;
    }

    @Override
    public void run() {
        while (herramienta != 0)
            switch (herramienta) {
                case 1:
                    System.out.println("Herramienta: llave inglesa");
                    mecaBloq.quieroLaLLave();
                    break;
                case 2:
                    System.out.println("Herramienta: carraca");
                    mecaBloq.yoQuieroLaLLave();
                    break;
            }
    /*
        //herramienta 1 = inglesa
        //herramienta 2 = carraca

        if (this.herramienta == 1)
            mecaBloq.quieroLaLLave();
        else
            mecaBloq.yoQuieroLaLLave();
         */
    }

} //fin class Hilo

public class Mecanicos{

    public static void main (String argv[]){

        Interbloqueos mecanico = new Interbloqueos();

        Thread h1 = new Thread(new Hilo(mecanico, 1));
        Thread h2 = new Thread(new Hilo(mecanico, 2));

        h1.start();
        h2.start();

        try{

            //hago join para que no me se terminen los dos tando h1 como h2
            h1.join();
            h2.join();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

} //fin class mecanicos