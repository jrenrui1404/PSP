class Personas {
    private long per1 = 0;
    private long per2 = 0;
    private long per3 = 0;
    private long per4 = 0;
    private long per5 = 0;

    private final Object bloqueoCuba1 = new Object();
    private final Object bloqueoCuba2 = new Object();

    public void cuba1(){
        synchronized (bloqueoCuba1){
            per1++;
            per3++;
            per5++;
        }
    }

    public long getCont1(){
        synchronized (bloqueoCuba1){
            return per1;

        }
    }

}




public class BloqueoCodigo {

    public static void main(String[] args) {

    }

}