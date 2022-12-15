/*
@author santi
@version v.1 28/11/21. Examen de Hilos
*/

/*
    * Como decía el ejercicio, sólo necesitamos dos hilos 
    de ejecución. Queremos que el programa acabe, cuando alguno
    de los hilos haya ganado.
*/

public class SimulaJugada {
    
    public static void main(String[] args) {
        PingPong tablero = new PingPong();
        Jugador h1 = new Jugador("santi", tablero);
        Jugador h2 = new Jugador("gabri", tablero);

        h1.start();
        h2.start();
        
        try{
            h1.join();
            h2.join();
            System.out.println("Partida emocionante finalizada......");

        }
        catch (InterruptedException exc){
            System.out.println("Partida interrumpida.");
        
        }
    }
}
