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
        System.out.println("Добро пожаловать " + ANSI_BRIGHT + "^. .^" + ANSI_RESET +  "\nВведите фразу для шифрования:");
        String phraseForEncryption = in.nextLine();
        char[] inputLetters = phraseForEncryption.toCharArray();
        Character[] letters;
        ArrayList<Character> alphabet = new ArrayList<>();
        letters = IntStream.range(0, inputLetters.length).mapToObj(i -> inputLetters[i]).toArray(Character[]::new);

        Arrays.stream(letters).sorted().distinct().forEach(alphabet::add);
        inputMessage(phraseForEncryption, alphabet);
        System.out.println("""
                Выберите размерность ключа:\s
                 2 -- для матрицы 2 x 2;\s
                 3 -- для матрицы 3 x 3;
                 4 -- для матрицы 4 x 4;""");
        int keySize = in.nextInt();

        switch (keySize) {
            case 2 -> encryptWith2(phraseForEncryption, alphabet);
            case 3 -> encryptWith3(phraseForEncryption, alphabet);
            case 4 -> encryptWith4(phraseForEncryption, alphabet);
        }

    }
    public static void encryptWith4(String phrase, ArrayList<Character> alphabet) {
        boolean success = false;
        int[][] keyMatrix = new int[4][4];
        while (!success) {
            System.out.println("Введите фразу для ключа из " + ANSI_BRIGHT + "16" + ANSI_RESET + " символов алфавита выше");
            String keyWorld = in.next();
            if (keyWorld.length() == 16) {
                success = true;
                boolean inpSuccess = true;
                for (int i = 0; i < 16; i++) {
                    keyMatrix[i / 4][i % 4] = alphabet.indexOf(keyWorld.charAt(i));
                    if (keyMatrix[i / 4][i % 4] == -1) {
                        success = false;
                        inpSuccess = false;
                        System.out.println(ANSI_BRIGHT + "Ошибка :( Символа " + ANSI_BLUE + keyWorld.charAt(i) +
                                ANSI_BRIGHT + " нет в алфавите" + ANSI_RESET);
                    }
                }
                if (inpSuccess) {
                    // проверяем, что определитель не ноль и не имеет общих делителей с длинной алфавита
                    int det = det4(keyMatrix);
                    if (det == 0) {
                        System.out.println(ANSI_BRIGHT + "Ошибка :( Введенный ключ запрещен (определитель равен 0)"
                                + ANSI_RESET);
                        success = false;
                    } else {
                        if (!checkDividers(alphabet.size(), det)) {
                            System.out.println(ANSI_BRIGHT + "Ошибка :( Введенный ключ запрещен (определитель и размер " +
                                    "алфавита имеют общие делители)"
                                    + ANSI_RESET);
                            success = false;
                        }
                    }
                }
            }
        }
        // дальше умножаем ключ на каждую из частей
        System.out.println("Разобьем фразу " + ANSI_BLUE + phrase + ANSI_RESET + " на фрагменты по 4 символа и" +
                " запишем соотвествующие коды:");
        int kostyl = 0;
        int[][] phraseMatrix = new int[phrase.length() / 4][4];
        for (int i = 0; i < phrase.length() / 4; i++) {
            char frst = phrase.charAt(kostyl);
            phraseMatrix[i][0] = alphabet.indexOf(frst);
            char scnd = phrase.charAt(kostyl + 1);
            phraseMatrix[i][1] = alphabet.indexOf(scnd);
            char thrd = phrase.charAt(kostyl + 2);
            phraseMatrix[i][2] = alphabet.indexOf(thrd);
            char frth = phrase.charAt(kostyl + 3);
            phraseMatrix[i][3] = alphabet.indexOf(frth);
            System.out.println(ANSI_BLUE + frst + scnd + thrd + ANSI_RESET + " -> " +
                    ANSI_BRIGHT + phraseMatrix[i][0] + " " + phraseMatrix[i][1] + " " + phraseMatrix[i][2] +
                    " " + phraseMatrix[i][3] + ANSI_RESET);
            kostyl += 4;
        }
        System.out.println("Проведем матричное умножение ключа на каждый вектор-фрагмент фразы и возьмем результат по модулю " +
                ANSI_BRIGHT + alphabet.size() + ANSI_RESET + ":");
        String encodedPhrase = "";
        for (int i = 0; i < phrase.length() / 4; i++) {
            encodedPhrase += mulMatrixMode(keyMatrix, phraseMatrix[i], 4, alphabet.size(), alphabet);
        }
        System.out.println("В результате шифрования получим фразу: " + ANSI_BLUE+ encodedPhrase + ANSI_RESET);

        System.out.println("Выберите дальнейшие действия: \n" + ANSI_BRIGHT + "1 -- Расшифровать полученное сообщение;" +
                "\n2 -- Повредить полученное сообщение;\n3 -- Зашифровать фразу другим ключем;" + ANSI_RESET);
        int choice = in.nextInt();

        System.out.println(Arrays.deepToString(keyMatrix));
    }
    public static void encryptWith3(String phrase, ArrayList<Character> alphabet) {
        boolean success = false;
        int[][] keyMatrix = new int[3][3];
        while (!success) {
            System.out.println("Введите фразу для ключа из " + ANSI_BRIGHT + "9" + ANSI_RESET + " символов алфавита выше");
            String keyWorld = in.next();
            if (keyWorld.length() == 9) {
                success = true;
                boolean inpSuccess = true;
                for (int i = 0; i < 9; i++) {
                    keyMatrix[i / 3][i % 3] = alphabet.indexOf(keyWorld.charAt(i));
                    if (keyMatrix[i / 3][i % 3] == -1) {
                        success = false;
                        inpSuccess = false;
                        System.out.println(ANSI_BRIGHT + "Ошибка :( Символа " + ANSI_BLUE + keyWorld.charAt(i) +
                                ANSI_BRIGHT + " нет в алфавите" + ANSI_RESET);
                    }
                }
                if (inpSuccess) {
                    // проверяем, что определитель не ноль и не имеет общих делителей с длинной алфавита
                    int det = det3(keyMatrix);
                    if (det == 0) {
                        System.out.println(ANSI_BRIGHT + "Ошибка :( Введенный ключ запрещен (определитель равен 0)"
                                + ANSI_RESET);
                        success = false;
                    } else {
                        if (!checkDividers(alphabet.size(), det)) {
                            System.out.println(ANSI_BRIGHT + "Ошибка :( Введенный ключ запрещен (определитель и размер " +
                                    "алфавита имеют общие делители)"
                                    + ANSI_RESET);
                            success = false;
                        }
                    }
                }
            }
        }
        // дальше умножаем ключ на каждую из частей
        System.out.println("Разобьем фразу " + ANSI_BLUE + phrase + ANSI_RESET + " на фрагменты по 4 символа и" +
                " запишем соотвествующие коды:");
        int kostyl = 0;
        int[][] phraseMatrix = new int[phrase.length() / 3][3];
        for (int i = 0; i < phrase.length() / 3; i++) {
            char frst = phrase.charAt(kostyl);
            phraseMatrix[i][0] = alphabet.indexOf(frst);
            char scnd = phrase.charAt(kostyl + 1);
            phraseMatrix[i][1] = alphabet.indexOf(scnd);
            char thrd = phrase.charAt(kostyl + 2);
            phraseMatrix[i][2] = alphabet.indexOf(thrd);
            System.out.println(ANSI_BLUE + frst + scnd + thrd + ANSI_RESET + " -> " +
                    ANSI_BRIGHT + phraseMatrix[i][0] + " " + phraseMatrix[i][1] + " " + phraseMatrix[i][2] + ANSI_RESET);
            kostyl += 3;
        }
        System.out.println("Проведем матричное умножение ключа на каждый вектор-фрагмент фразы и возьмем результат по модулю " +
                ANSI_BRIGHT + alphabet.size() + ANSI_RESET + ":");
        String encodedPhrase = "";
        for (int i = 0; i < phrase.length() / 3; i++) {
            encodedPhrase += mulMatrixMode(keyMatrix, phraseMatrix[i], 3, alphabet.size(), alphabet);
        }
        System.out.println("В результате шифрования получим фразу: " + ANSI_BLUE+ encodedPhrase + ANSI_RESET);

        System.out.println("Выберите дальнейшие действия: \n" + ANSI_BRIGHT + "1 -- Расшифровать полученное сообщение;" +
                "\n2 -- Повредить полученное сообщение;\n3 -- Зашифровать фразу другим ключем;"  + ANSI_RESET);
        int choice = in.nextInt();

        System.out.println(Arrays.deepToString(keyMatrix));

    }
    public static void encryptWith2(String phrase, ArrayList<Character> alphabet) {
        boolean success = false;
        int[][] keyMatrix = new int[2][2];
        while (!success) {
            System.out.println("Введите фразу для ключа из " + ANSI_BRIGHT + "4" + ANSI_RESET + " символов алфавита выше");
            String keyWorld = in.next();
            if (keyWorld.length() == 4) {
                success = true;
                boolean inpSuccess = true;
                for (int i = 0; i < 4; i++) {
                    keyMatrix[i / 2][i % 2] = alphabet.indexOf(keyWorld.charAt(i));
                    if (keyMatrix[i / 2][i % 2] == -1) {
                        success = false;
                        inpSuccess = false;
                        System.out.println(ANSI_BRIGHT + "Ошибка :( Символа " + ANSI_BLUE + keyWorld.charAt(i) +
                                ANSI_BRIGHT + " нет в алфавите" + ANSI_RESET);
                    }
                }
                if (inpSuccess) {
                    // проверяем, что определитель не ноль и не имеет общих делителей с длинной алфавита
                    int det = det2(keyMatrix);
                    if (det == 0) {
                        System.out.println(ANSI_BRIGHT + "Ошибка :( Введенный ключ запрещен (определитель равен 0)"
                                + ANSI_RESET);
                        success = false;
                    } else {
                        if (!checkDividers(alphabet.size(), det)) {
                            System.out.println(ANSI_BRIGHT + "Ошибка :( Введенный ключ запрещен (определитель и размер " +
                                    "алфавита имеют общие делители)"
                                    + ANSI_RESET);
                            success = false;
                        }
                    }
                }
            }
        }
        // дальше умножаем ключ на каждую из частей
        System.out.println("Разобьем фразу " + ANSI_BLUE + phrase + ANSI_RESET + " на фрагменты по 2 символа и" +
                " запишем соотвествующие коды:");
        int kostyl = 0;
        int[][] phraseMatrix = new int[phrase.length() / 2][2];
        for (int i = 0; i < phrase.length() / 2; i++) {
            char frst = phrase.charAt(kostyl);
            phraseMatrix[i][0] = alphabet.indexOf(frst);
            char scnd = phrase.charAt(kostyl + 1);
            phraseMatrix[i][1] = alphabet.indexOf(scnd);
            System.out.println(ANSI_BLUE + frst + scnd + ANSI_RESET + " -> " +
                    ANSI_BRIGHT + phraseMatrix[i][0] + " " + phraseMatrix[i][1] + ANSI_RESET);
            kostyl += 2;
        }
        System.out.println("Проведем матричное умножение ключа на каждый вектор-фрагмент фразы и возьмем результат по модулю " +
                ANSI_BRIGHT + alphabet.size() + ANSI_RESET + ":");
        String encodedPhrase = "";
        for (int i = 0; i < phrase.length() / 2; i++) {
            encodedPhrase += mulMatrixMode(keyMatrix, phraseMatrix[i], 2, alphabet.size(), alphabet);
        }
        System.out.println("В результате шифрования получим фразу: " + ANSI_BLUE+ encodedPhrase + ANSI_RESET);

        System.out.println("Выберите дальнейшие действия: \n" + ANSI_BRIGHT + "1 -- Расшифровать полученное сообщение;" +
                "\n2 -- Повредить полученное сообщение;\n3 -- Зашифровать фразу другим ключем;" + ANSI_RESET);
        int choice = in.nextInt();
        System.out.println(Arrays.deepToString(keyMatrix));

    }
    public static String mulMatrixMode(int[][] a, int[] b, int len, int mode, ArrayList<Character> alphabet) {
        int[][] answer = new int[len][1];
        String back = "";
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                answer[i][0] += a[i][j] * b[j];
            }
            answer[i][0] = answer[i][0] % mode;
            back += alphabet.get(answer[i][0]).toString();
        }
       for (int i = 0; i < len; i++) {
           if (i == (len / 2)) {
               System.out.println(ANSI_BRIGHT + b[i] + ANSI_RESET + "  ->  " + ANSI_BRIGHT + answer[i][0] + ANSI_RESET +
                       "  ->  " + ANSI_BLUE + alphabet.get(answer[i][0]) + ANSI_RESET);
           } else
               System.out.println(ANSI_BRIGHT + b[i] + ANSI_RESET + "      " + ANSI_BRIGHT + answer[i][0] + ANSI_RESET +
                       "  ->  " + ANSI_BLUE + alphabet.get(answer[i][0]) + ANSI_RESET);
       }
       System.out.println("-----------");

       return back;
    }
    public static int det2(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }
    public static int det3(int[][] ar) {
        return ar[0][0] * ar[1][1] * ar[2][2] + ar[0][1] * ar[1][2] * ar[2][0] +
                ar[1][0] * ar[2][1] * ar[0][2] - ar[2][0] * ar[1][1] * ar[0][2] -
                ar[0][0] * ar[2][1] * ar[1][2] - ar[0][1] * ar[1][0] * ar[2][2];
    }

    public static int det4(int[][] a) {
        int answer = 0;
        int[][] minor = new int[3][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int col = 0;
                for (int k = 0; k < 4; k++) {
                    if (k != i)
                        minor[j][col++] = a[j + 1][k];
                }
            }
            if ((i % 2) == 0)
                answer += a[0][i] * det3(minor);
            else
                answer -= a[0][i] * det3(minor);
        }
        return answer;
    }

    //проверяем, что определитель не ноль и не имеет общих делителей с длинной алфавита
    public static boolean checkDividers(int alph, int det ) {
        if ((alph > 1) && (det > 1)) {
            for (int i = 2; i < Math.sqrt(alph); i++) {
                if (((alph % i) == 0) && ((det % i) == 0))
                    return false;
            }
        }
        return true;
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
