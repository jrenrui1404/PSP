public class Principal {

    public static void main(String[] args) throws InterruptedException {
        Mecanico m1 = new Mecanico("Paco", 1);
        Mecanico m2 = new Mecanico("Manu", 2);

        Thread h1 = new Thread(m1);
        Thread h2 = new Thread(m2);

        h1.start();
        h2.start();

        try{

            h1.join();
            h2.join();

        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}