package task_2;

import java.util.Scanner;

public class Hack {
    public static final String ANSI_BRIGHT = "\u001B[38;5;198m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Добро пожаловать " + ANSI_BLUE + "^. .^" + ANSI_RESET +  "\nВведите первую фразу из 12 символов для шифрования:");
        String firstPhraseForEncryption = in.nextLine();

        System.out.println("Введите вторую фразу из 12 символов для шифрования:");
        String secondPhraseForEncryption = in.nextLine();


    }
}
