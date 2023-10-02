package task_4;

import java.util.Scanner;

import static task_1.Encryption.transMatrix;

public class PrisonersCode {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] vectors = new int[8][4];
        System.out.println("Введите начальные векторы: ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                vectors[i][j] = in.nextInt();
            }
        }
        System.out.println("Введите начальные G: ");
        int[][] g = new int[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                g[i][j] = in.nextInt();
            }
        }
        int[][] encodedMessage = new int[8][8];
        for (int i = 0; i < 8; i++) {
            encodedMessage[i] = mulVectorAndG(vectors[i], g);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(encodedMessage[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Введите начальные H: ");
        int[][] H = new int[8][4];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                H[i][j] = in.nextInt();
            }
        }
        System.out.println("Введите поврежденное: ");
        int[][] newEncodedMessage = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newEncodedMessage[i][j] = in.nextInt();
            }
        }
        int[][] transNewEncodedMessage = transMatrix(newEncodedMessage,8 );
        int[][] symptom = new int[8][4];
        int[][] colSymptom = new int[8][4];
        for (int i = 0; i < 8; i++) {
            symptom[i] = mulVectorAndG(newEncodedMessage[i], H);
           colSymptom[i] = mulVectorAndG(transNewEncodedMessage[i], H);
        }
        System.out.println("------------------------");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(symptom[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(colSymptom[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[] mulVectorAndG(int[] v, int[][] g) {
        int[] answer = new int[g[0].length];
        for (int i = 0; i < v.length; i++) {
            if (v[i] == 1) {
                for (int j = 0; j < g[0].length; j++) {
                    answer[j] += g[i][j];
                    answer[j] = answer[j] % 2;
                }
            }
        }
        return answer;
    }
}
