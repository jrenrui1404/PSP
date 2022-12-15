/*
@author santi
@version v.1 28/11/21. Examen de Hilos
*/

/*
    - Esta clase, no controlará la sincronización de los hilos
    - El método más llamativo es el finPartida, que anota cuando
    un jugador llega a los 15 puntos y pone una variable booleana a true.
    Necesitamos saber cuando se ha llegado al final de la partida y eso
    lo controlamos con la variable finPartida.
    - La variable turno, la tenemos para establecer el turno de zip-zap de
    cada uno de los hilos. Primero un hilo, luego el otro y viceversa.
    Siempre empezamos con el turno 1.

*/
public class PingPong {
    private int puntuacionJugador1 = 0;
    private int puntuacionJugador2= 0;
    private int turno=1;
    private boolean finPartida = false;
    private int quienHaGanado =0;
    

    synchronized public int getPuntuacionJugador1(){return this.puntuacionJugador1;}

    synchronized public int getPuntuacionJugador2(){return this.puntuacionJugador2;}

    synchronized public int getTurno(){return this.turno;}

    synchronized public void setTurno(int turno){this.turno = turno;}

    synchronized public void anotaPuntuacionJugador1(){this.puntuacionJugador1++;}

    synchronized public void anotaPuntuacionJugador2(){this.puntuacionJugador2++;}

    synchronized public boolean getFinPartida(){return this.getFinPartida();}

    synchronized public int ganador(){return this.quienHaGanado;}

    synchronized public boolean finPartida(){
        if (puntuacionJugador1==15){
            quienHaGanado = 1;
            finPartida = true;
        }
        else   
            if (puntuacionJugador2==15){
                quienHaGanado = 2;
                finPartida = true;
            }
        return finPartida;   
        
           

    }
   
    
    
}
