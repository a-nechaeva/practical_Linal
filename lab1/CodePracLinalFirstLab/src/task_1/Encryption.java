package task_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Encryption {
    public static Scanner in = new Scanner(System.in);
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PINK = "\u001B[35m";
    public static final String ANSI_BRIGHT = "\u001B[38;5;198m";
    public static void main(String[] args) {
        System.out.println("Добро пожаловать " + ANSI_BRIGHT + "^. .^" + ANSI_RESET +  "\nВвведите фразу для шифрования:");
        String phraseForEncryption = in.nextLine();
        char[] inputLetters = phraseForEncryption.toCharArray();
        Character[] letters;
        ArrayList<Character> alphabet = new ArrayList<>();
        letters = IntStream.range(0, inputLetters.length).mapToObj(i -> inputLetters[i]).toArray(Character[]::new);

        Arrays.stream(letters).sorted().distinct().forEach(alphabet::add);
        inputMessage(phraseForEncryption, alphabet);
        System.out.println("Выберите размерность ключа: \n 2 -- для матрицы 2 x 2; \n 3 -- для матрицы 3 x 3;" +
                "\n 4 -- для матрицы 4 x 4;");
        int keySize = in.nextInt();

        switch (keySize) {
            case 2 -> encryptWith2(phraseForEncryption, alphabet);
            case 3 -> encryptWith3(phraseForEncryption, alphabet);
            case 4 -> encryptWith4(phraseForEncryption, alphabet);
        }

    }
    public static void encryptWith4(String phrase, ArrayList<Character> alphabet) {

    }
    public static void encryptWith3(String phrase, ArrayList<Character> alphabet) {

    }
    public static void encryptWith2(String phrase, ArrayList<Character> alphabet) {
        System.out.println("Введите фразу для ключа из " + ANSI_BRIGHT + "4" + ANSI_RESET + " символов алфавита выше");
        String keyWorld = in.next();
        if (keyWorld.length() != 4) {
            System.out.println("Введите фразу для ключа из " + ANSI_BRIGHT + "4" + ANSI_RESET + " символов алфавита выше");
        } else {
            int[][] keyMatrix = new int[2][2];
            for (int i = 0; i < 4; i++) {
                keyMatrix[i / 2][i % 2] = alphabet.indexOf(keyWorld.charAt(i));
                System.out.println( keyMatrix[i / 2][i % 2]);
            }
        }

    }

    public static void inputMessage(String input, ArrayList<Character> alphabet) {
        System.out.println("Фраза для шифрования: " + ANSI_BLUE + input + ANSI_RESET +  "\n");
        System.out.println("В алфавите символов: " + ANSI_PINK + alphabet.size() + ANSI_RESET);
        System.out.println("| Код | Символ |");
        System.out.println("----------------");
        for (int i = 0; i < alphabet.size(); i++) {
            System.out.println("|  " + ANSI_BRIGHT + i + ANSI_RESET + "  |   " + ANSI_BLUE + alphabet.get(i) +
                    ANSI_RESET + "   |");
            System.out.println("----------------");
        }
    }
}
