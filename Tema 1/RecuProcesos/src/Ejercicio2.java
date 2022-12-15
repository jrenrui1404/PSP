import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder("find", "copia.txt", "\\C");
        //ProcessBuilder processBuilder = new ProcessBuilder("find", "\"jose\"","/copia.txt");

        processBuilder.redirectInput();

        Process process = processBuilder.start();

        Scanner scanner;
        scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }

        String ficheroACopiar = "copiaFichero.txt";
        File file = new File("./" + ficheroACopiar);

        /*processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT).redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectInput(fichero);*/
        processBuilder.redirectOutput(file);
        processBuilder.start();
        process.waitFor();
        System.out.println("Fichero copiado");

    }

}
