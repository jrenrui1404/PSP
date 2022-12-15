import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConvertirCadena {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fichero = new Scanner(new File("C:\\Users\\tarde\\Desktop\\2º DAM\\Programación de servicios y procesos\\Tema 1\\RecuProcesos\\src\\salida.txt"));
        while (fichero.hasNextLine())
            System.out.println(fichero.nextLine().toUpperCase());
        fichero.close();
    }
}
