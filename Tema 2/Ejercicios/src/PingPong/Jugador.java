package PingPong;/*
@author santi
@version v.1 28/11/21. Examen de Hilos
*/

/*
    - En esta posible solución del problema, hemos considerado que los
    hilos sean los que realizen la sincronización mediante estados.
    - Otra posible solución, es incluir métodos de sincronización dentro
    de la clase PinPon, pero es una manera muy similiar al ejercicio que
    puse en la plataforma del tic-tac.
    - La idea es controlar en todo momento el turno de cada uno de los hilos
    y muy importante, cuando parar la partida. Para parar la partida, tenemos
    un if (juego.finPartida). En este método, indicará al hilo que debe finalizar
    su ejecución y hacer un return. Automáticamente finalizará.
    - El método simulaQuienGanaElPunto, es una forma de simular de manera aleatoria
    quién de los dos se anota el punto. Para simular un número de golpeo por punto
    utilizamos el mismo valor aleatorio que nos sirve para verificar si es par
    se lo queda santi y si es impar se lo queda Gabi. El aleatorio se genera dentro del
    método.
    - Lo más importante, como hemos dicho es controlar o sincronizar el turno de
    cada uno de los juegadores (hilos), para ello la clase PingPong.Jugador, utiliza el  nombre
    para que cada hilo ejecute su parte correspondiente. 
    Será el primer if (this.nombre.equals("santi"))
    - Cuando entra santi o gabriel, es importante anotar el turno de cada uno, es decir
    va a golpear santi con el turno a 1 o gabi con el turno a 2.
    Antes de jugar el punto, se debe comprobar si ha ganado alguno. Si estamos con santi
    y resulta que he ganado yo, lo que tengo que hacer es notificarlo en pantalla e inmediatamente
    después poner el turno de gabi a 2 y notificarlo porque está bloqueado. Si es Gabi
    el que comprueba si ha ganado alguno, hará lo mismo que es establecer el turno a santi
    a 1 y notificarlo para que se desbloquee y pueda comprobar también que gabi a ganado
    y deberá finalizar con un return.
        - Si ninguno ha ganado aún, lo que toca es simular el golpe y ver quien se lo lleva
        para ello, ejecutamos el método simulaQuienGanaElPunto, que incrementará el contador
        de puntos de alguno de los dos, de manera aleatoria. Después de ejecutar el golpe,
        debemos dormir al hilo por un tiempo de espera.
    - Lo importante después de que se realize el golpe de santi o gabi, es 
    poner el turno al siguiente jugador y DESPERTARLO con el notify. Eso provoca que
    el otro hilo reciba la notificación de desbloqueo pero el hilo actual, entrará en un
    bucle while que ejecutará una espera activa wait, hasta que el otro hilo no cambie el turno
    del jugador. Eso es lo que hacemos con el  while (juego.getTurno()==2) cuando santi
    finaliza su espera, cambia el turno a Gabi y lo notifica. Este se desbloqueará
    cuando Gabi ejecute su correspondiente golpe y vuelva a notificar a santi.
    - Como he dicho, tiene variaas formas de resolverse y otro sería utilizando 
    semáforos, con la característica que no tenemos que utilizar métodos sincronizados en el
    recurso PinPon y en principio utilizar un semáforo para controlar el bloqueo de los dos
    hilos.
    - Sería muy interesante, volver a realizar este mismo ejercicio pero quitando a los
    hilos de la necesidad de la sincronización·

    ----Saludos.-----
*/

import java.util.Random;

public class Jugador extends Thread{
    private String nombre;
    private PingPong juego;
    public Random alea;

    public Jugador(String nombre, PingPong juego){
        alea = new Random();
        this.nombre = nombre;
        this.juego = juego;
    }

    public void simulaQuienGanaElPunto(){
        int v = alea.nextInt(10) + 1;

        if (v%2 == 0) {
            this.juego.anotaPuntuacionJugador1(); 
            System.out.println("Anota el jugador Santi por "+v+" golpes");
        }
        else {
            this.juego.anotaPuntuacionJugador2();
            System.out.println("Anota el jugador Gagi por "+v+" golpes");
        }
    
       

    }

    @Override
    public void run(){
        while (true){
            try{
                if (this.nombre.equals("santi")){
                    //es el jugador santi
                    synchronized(juego){
                        juego.setTurno(1); //me toca a mi. 
                        if (juego.finPartida()){  //Hay alguno que haya ganado la partida?????
                           if (juego.ganador() == 1)  //Si soy yo, informo, y pongo el turno a Gabi para que no se bloquee.
                                System.out.println("Ha ganado el jugador Santi, por un total de "+this.juego.getPuntuacionJugador1()+" frente a Gabriel por un total de "+this.juego.getPuntuacionJugador2());
                            juego.setTurno(2);  
                            juego.notify();
                            return;
                        }
                    }

                    simulaQuienGanaElPunto();
                    sleep(alea.nextInt(200)+50);
                    synchronized (juego){
                        juego.setTurno(2);
                        juego.notify();

                        while (juego.getTurno()==2)
                            juego.wait();
                    }
                   
                
                }
                 else{  
                    //es el jugador Gagi
                    synchronized(juego){
                        juego.setTurno(2);  //le toca a Gabi
                        if (juego.finPartida()){
                            if (juego.ganador() == 2)
                                System.out.println("Ha ganado el jugador Gabriel, por un total de "+this.juego.getPuntuacionJugador2()+" frente a Santi por un total de "+this.juego.getPuntuacionJugador1());
                            juego.setTurno(1);                        
                            juego.notify();
                             return;
                         }
                    }

                    simulaQuienGanaElPunto();
                    sleep(alea.nextInt(200)+50);
                    synchronized(juego){
                        juego.setTurno(1);
                        juego.notify();
                        
                         while (juego.getTurno()==1)
                            juego.wait();

                        }
                    }
            }
            catch (InterruptedException exc){
                System.out.println("PingPong.Jugador santi interrumpido.");
             }
        }
     
    }//fin run
    
}
