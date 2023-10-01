package task_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import static task_1.Encryption.*;

public class Hack {
    public static final String ANSI_BRIGHT = "\u001B[38;5;198m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Добро пожаловать " + ANSI_BLUE + "^. .^" + ANSI_RESET +  "\nВведите первую фразу из 12 символов для шифрования:");
        boolean success = false;
        String firstPhraseForEncryption = "";
        String secondPhraseForEncryption = "";
        while (!success) {
            firstPhraseForEncryption = in.nextLine();
            if (firstPhraseForEncryption.length() != 12)
                System.out.println("Введите первую фразу из 12 символов для шифрования:");
            else success = true;
        }
        success = false;
        while (!success) {
            System.out.println("Введите вторую фразу из 12 символов для шифрования:");
            secondPhraseForEncryption = in.nextLine();
            if (secondPhraseForEncryption.length() == 12)
                success = true;
        }
        // БЕРЕМ АЛФАВИТ ТОЛЬКО ИЗ 1 СТРОКИ
        char[] inputLetters = firstPhraseForEncryption.toCharArray();
        Character[] letters;
        ArrayList<Character> alphabet = new ArrayList<>();
        letters = IntStream.range(0, inputLetters.length).mapToObj(i -> inputLetters[i]).toArray(Character[]::new);

        Arrays.stream(letters).sorted().distinct().forEach(alphabet::add);
       // int[][] generatedKey = generateKey2(alphabet);
        System.out.println("Идет работа с первой фразой...");
        encryptWith2(firstPhraseForEncryption, alphabet);
        System.out.println("Идет работа со второй фразой...");
        encryptWith2(secondPhraseForEncryption, alphabet);


    }
    public static void tryToFindKey2(ArrayList<Character> alphabet, String trueString, String codeString, int[][] trueKey) {
        int[][] cSMatrix = new int[2][2];
        int[][] tSMatrix = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cSMatrix[i][j] = alphabet.indexOf(codeString.charAt(2 * i + j));
                tSMatrix[i][j] = alphabet.indexOf(trueString.charAt(2 * i + j));

            }
        }
        cSMatrix = transMatrix(cSMatrix, 2);
        tSMatrix = transMatrix(tSMatrix, 2);

        int[][] revTSMatrix = transModeMatrix(tSMatrix, alphabet.size());
        int[][] myKey = mulMx(cSMatrix,revTSMatrix, 2);

        System.out.println("Мой ключ:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(myKey[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Настоящий ключ:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(trueKey[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[][] mulMx(int[][] a, int[][] b, int n) {
        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    answer[i][j] += a[i][k] * b[k][j];
                }
                answer[i][j] = correctRemainder(answer[i][j], 11);
            }
        }
        return answer;
    }
    public static int[][] generateKey2(ArrayList<Character> alphabet) {
        int[][] answer = new int[2][2];
        boolean success = false;
        while (!success) {
            success = true;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    answer[i][j] = ((int) (Math.random() * 10)) % alphabet.size();
                }
            }
            int det = det2(answer);
            if ((!checkDividers(alphabet.size(), det)) || (det == 0))
                success = false;
        }
        return answer;
    }

}
