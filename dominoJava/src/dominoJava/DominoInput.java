package dominoJava;

import java.util.Scanner;

public class DominoInput {
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

