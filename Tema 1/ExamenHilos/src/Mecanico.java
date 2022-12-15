public class Mecanico extends Thread {

    private String nombre;
    private int llave;

    public Mecanico(String nombre, int llave) {
        this.nombre = nombre;
        this.llave = llave;
    }

    @Override
    public void run() {
        while (true)
            switch (llave) {
                case 1:
                    System.out.println("Inglesa");
                    break;
                case 2:
                    System.out.println("Carraca");
                    break;
            }
    }

        /*
        llave = "inglesa";
        llave = "carraca";
        if (llave == "inglesa"){
            System.out.printf("Inglesa");
        }else {
            System.out.println("Carraca");
        }
    }*/
}
