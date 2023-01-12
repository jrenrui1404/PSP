package EjemploTarjetas;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class infoTarjetasRed {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> eths;

        eths = NetworkInterface.getNetworkInterfaces();

        int i = 1;
        while (eths.hasMoreElements()) {
            System.out.printf("Tarjeta de red numero %d", i);
            i++;
            mostrarInfo(eths.nextElement());
        }
    }

    private static void mostrarInfo(NetworkInterface interfaz) throws SocketException {

        System.out.printf("Nombre de la tarjeta de red %s \n", interfaz.getName());
        System.out.printf("Nombre de la tarjeta de red %s \n", interfaz.getDisplayName());
        System.out.printf("Es loopback o no: %s \n", interfaz.isLoopback() ? "si" : "no");
        System.out.printf("Soporta Multicast o no: %s \n", interfaz.supportsMulticast() ? "si" : "no");
        System.out.printf("MTU: %d \n", interfaz.getMTU());

        //MAC
        byte[] mac = interfaz.getHardwareAddress();
        System.out.printf("MAC de la tarjeta de red %s \n", interfaz.getName());
        boolean primerobyte = true;

        if (mac!=null){
            for (int i = 0; i<mac.length; i++) {
                if (!primerobyte){
                    System.out.println(":");
                }
                System.out.printf("%x", mac[i]);
                primerobyte = false;
            }
        }
    }
}
