package EjemploTarjetas;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class tarjetasRed {
    public static void main(String[] args) throws SocketException {
        /*Tipo de datos almacena infor interfaces de red*/

        Enumeration<NetworkInterface> eths;

        eths = NetworkInterface.getNetworkInterfaces();

        for (eths = NetworkInterface.getNetworkInterfaces(); eths.hasMoreElements();){
            System.out.printf("Tarjeta en red %s\n", eths.nextElement().getDisplayName());
        }

        System.out.println();

        while (eths.hasMoreElements()) {
            System.out.printf("Tarjeta en red %s\n", eths.nextElement().getName());
        }

    }
}
