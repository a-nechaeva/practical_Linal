package task_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import static task_1.evklid.extEvklyd;


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
    public static int correctRemainder(int numb, int mode) {
          if (numb >= 0)
            return numb % mode;
        else {
            while (numb < 0) {
                numb += mode;
            }
            return numb;
        }
    }

    public static void destroy(ArrayList<Character> alphabet, String truePhrase, String encodePhrase, int[][] key) {
        System.out.println("Начальная фраза: " + ANSI_BLUE + truePhrase + ANSI_RESET);
        System.out.println("Зашифрованная фраза: " + ANSI_BLUE + encodePhrase + ANSI_RESET);
        System.out.println("Введите поврежденную фразу:");
        String hurt = in.next();
        System.out.println("Расшифровать (1 / 0)? " + ANSI_BLUE + hurt + ANSI_RESET);
        int choice = in.nextInt();

        switch (choice) {
            case 1 -> decipher(alphabet, truePhrase, hurt, key);
        }
    }
    public static void decipher(ArrayList<Character> alphabet, String truePhrase, String hurt, int[][] key) {
        switch (key.length) {
            case 2 -> decipherWith2(alphabet, truePhrase, hurt, key);
            case 3 -> decipherWith3(alphabet, truePhrase, hurt, key);
            case 4 -> decipherWith4(alphabet, truePhrase, hurt, key);
        }
    }

    public static void decipherWith2(ArrayList<Character> alphabet, String truePhrase, String hurt, int[][] key) {
        System.out.println("Разобьем фразу " + ANSI_BLUE + hurt + ANSI_RESET + " на фрагменты по 2 символа и" +
                " запишем соотвествующие коды:");

        int kostyl = 0;
        int[][] phraseMatrix = new int[hurt.length() / 2][2];
        for (int i = 0; i < hurt.length() / 2; i++) {
            char frst = hurt.charAt(kostyl);
            phraseMatrix[i][0] = alphabet.indexOf(frst);
            char scnd = hurt.charAt(kostyl + 1);
            phraseMatrix[i][1] = alphabet.indexOf(scnd);
            System.out.println(ANSI_BLUE + frst + scnd + ANSI_RESET + " -> " +
                    ANSI_BRIGHT + phraseMatrix[i][0] + " " + phraseMatrix[i][1] + ANSI_RESET);
            kostyl += 2;
        }
        System.out.println("Найдем обратную по модулю " + ANSI_PINK + alphabet.size() + ANSI_RESET + " матрицу ключа.");
        System.out.println("Матрица ключа выглядела так:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(ANSI_PINK + key[i][j] + ANSI_RESET + " ");
            }
            System.out.println();
        }
        System.out.println("Обратная по модулю " + ANSI_PINK + alphabet.size() + ANSI_RESET + " матрица ключа:");
        //ЭТО СОЮЗНАЯ МАТРИЦА ПОКА ЧТО!!!!
        int[][] reversedMatrix = transModeMatrix(key, alphabet.size());

        // ОБРАТНЫЙ ЭЛЕМЕНТ ДЕТЕРМИНАНТА В КОЛЬЦЕ ПО МОДУЛЮ ДЛИНЫ АЛФАВИТА
        int reverseDet = revDet(det2(key), alphabet.size());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                reversedMatrix[i][j] = correctRemainder(reversedMatrix[i][j] * reverseDet, alphabet.size());
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(reversedMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Проведем матричное умножение обратной матрицы ключа на каждый вектор-фрагмент фразы и возьмем результат по модулю " +
                ANSI_BRIGHT + alphabet.size() + ANSI_RESET + ":");
        String encodedPhrase = "";
        for (int i = 0; i < hurt.length() / 2; i++) {
            encodedPhrase += mulMatrixMode(reversedMatrix, phraseMatrix[i], 2, alphabet.size(), alphabet);
        }
        System.out.println("В результате дешифрования получим фразу: " + ANSI_BLUE+ encodedPhrase + ANSI_RESET);
        System.out.println("Начальная фраза: " + ANSI_BLUE + truePhrase + ANSI_RESET);

    }
    public static int revDet(int det, int alphabetLen) {
        int x =  extEvklyd(det, alphabetLen);

        return x;
    }
// ТРАНСПОНИРОВАННАЯ МАТРИЦА АЛГЕБРАИЧЕСКИХ ДОПОЛНЕНИЙ, ВЗЯТЫХ ПО МОДУЛЮ
    public static int[][] transModeMatrix(int[][] key, int mode) {
        int det = 1;
        switch (key.length) {
            case 2 -> det = det2(key);
            case 3 -> det = det3(key);
            case 4 -> det = det4(key);
        }
        int[][] matrix = algebraicAdditionsMatrix(key);

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                //matrix[i][j] = correctRemainder(matrix[i][j], mode);
                matrix[i][j] = matrix[i][j] % mode;
            }
        }



        for (int i = 0; i < key.length; i++) {
           for (int j = i + 1; j < key.length; j++) {
               int t = matrix[i][j];
               matrix[i][j] = matrix[j][i];
               matrix[j][i] = t;
           }
        }
        // System.out.println(Arrays.deepToString(matrix));
        return matrix;
    }

    public static int[][] algebraicAdditionsMatrix(int[][] matrix) {
        int n = matrix.length;
        // System.out.println(Arrays.deepToString(matrix));
        int[][] answer = new int[n][n];
        switch (n) {
            case 2 -> {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (((i + j) % 2) == 0)
                            answer[i][j] = matrix[1 - i][1 - j];
                        else
                            answer[i][j] = -matrix[1 - i][1 - j];
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int[][] minor = new int[2][2];
                        int row = 0;
                        for (int k = 0; k < 3; k++) {
                            int col = 0;
                            if (k != i) {
                                for (int l = 0; l < 3; l++) {
                                    if ((l != j)) {
                                        minor[row][col] = matrix[k][l];
                                        ++col;
                                    }
                                }
                                ++row;
                            }
                        }
                        if ((i + j) % 2 == 0)
                            answer[i][j] = det2(minor);
                        else
                            answer[i][j] = -det2(minor);
                    }
                }
            }
            case 4 -> {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        int[][] minor = new int[3][3];
                        int row = 0;
                        for (int k = 0; k < 4; k++) {
                            int col = 0;
                            if(k != i) {
                                for (int l = 0; l < 4; l++) {
                                    if ((l != j)) {
                                        minor[row][col] = matrix[k][l];
                                        ++col;
                                    }
                                }
                            ++row;
                            }
                        }
                        if ((i + j) % 2 == 0)
                            answer[i][j] = det3(minor);
                        else
                            answer[i][j] = -det3(minor);
                    }
                }
            }
        }

        return answer;
    }

    public static void decipherWith3(ArrayList<Character> alphabet, String truePhrase, String hurt, int[][] key) {
        System.out.println("Разобьем фразу " + ANSI_BLUE + hurt + ANSI_RESET + " на фрагменты по 3 символа и" +
                " запишем соотвествующие коды:");

        int kostyl = 0;
        int[][] phraseMatrix = new int[hurt.length() / 3][3];
        for (int i = 0; i < hurt.length() / 3; i++) {
            char frst = hurt.charAt(kostyl);
            phraseMatrix[i][0] = alphabet.indexOf(frst);
            char scnd = hurt.charAt(kostyl + 1);
            phraseMatrix[i][1] = alphabet.indexOf(scnd);
            char thrd = hurt.charAt(kostyl + 2);
            phraseMatrix[i][2] = alphabet.indexOf(thrd);
            System.out.println(ANSI_BLUE + frst + scnd + thrd + ANSI_RESET + " -> " +
                    ANSI_BRIGHT + phraseMatrix[i][0] + " " + phraseMatrix[i][1] + " " + phraseMatrix[i][2] + ANSI_RESET);
            kostyl += 3;
        }
        System.out.println("Найдем обратную по модулю " + ANSI_PINK + alphabet.size() + ANSI_RESET + " матрицу ключа.");
        System.out.println("Матрица ключа выглядела так:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ANSI_PINK + key[i][j] + ANSI_RESET + " ");
            }
            System.out.println();
        }
        System.out.println("Обратная по модулю " + ANSI_PINK + alphabet.size() + ANSI_RESET + " матрица ключа:");
        //ЭТО СОЮЗНАЯ МАТРИЦА ПОКА ЧТО!!!!
        int[][] reversedMatrix = transModeMatrix(key, alphabet.size());

        // ОБРАТНЫЙ ЭЛЕМЕНТ ДЕТЕРМИНАНТА В КОЛЬЦЕ ПО МОДУЛЮ ДЛИНЫ АЛФАВИТА
        int reverseDet = revDet(det3(key), alphabet.size());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                reversedMatrix[i][j] = correctRemainder(reversedMatrix[i][j] * reverseDet, alphabet.size());
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(reversedMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Проведем матричное умножение обратной матрицы ключа на каждый вектор-фрагмент фразы и возьмем результат по модулю " +
                ANSI_BRIGHT + alphabet.size() + ANSI_RESET + ":");
        String encodedPhrase = "";
        for (int i = 0; i < hurt.length() / 3; i++) {
            encodedPhrase += mulMatrixMode(reversedMatrix, phraseMatrix[i], 3, alphabet.size(), alphabet);
        }
        System.out.println("В результате дешифрования получим фразу: " + ANSI_BLUE+ encodedPhrase + ANSI_RESET);
        System.out.println("Начальная фраза: " + ANSI_BLUE + truePhrase + ANSI_RESET);

    }
    public static void decipherWith4(ArrayList<Character> alphabet, String truePhrase, String hurt, int[][] key) {
        System.out.println("Разобьем фразу " + ANSI_BLUE + hurt + ANSI_RESET + " на фрагменты по 2 символа и" +
                " запишем соотвествующие коды:");

        int kostyl = 0;
        int[][] phraseMatrix = new int[hurt.length() / 4][4];
        for (int i = 0; i < hurt.length() / 4; i++) {
            char frst = hurt.charAt(kostyl);
            phraseMatrix[i][0] = alphabet.indexOf(frst);
            char scnd = hurt.charAt(kostyl + 1);
            phraseMatrix[i][1] = alphabet.indexOf(scnd);
            char thrd = hurt.charAt(kostyl + 2);
            phraseMatrix[i][2] = alphabet.indexOf(thrd);
            char frth = hurt.charAt(kostyl + 3);
            phraseMatrix[i][3] = alphabet.indexOf(frth);
            System.out.println(ANSI_BLUE + frst + scnd + thrd + ANSI_RESET + " -> " +
                    ANSI_BRIGHT + phraseMatrix[i][0] + " " + phraseMatrix[i][1] + " " + phraseMatrix[i][2] +
                    " " + phraseMatrix[i][3] + ANSI_RESET);
            kostyl += 4;
        }
        System.out.println("Найдем обратную по модулю " + ANSI_PINK + alphabet.size() + ANSI_RESET + " матрицу ключа.");
        System.out.println("Матрица ключа выглядела так:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(ANSI_PINK + key[i][j] + ANSI_RESET + " ");
            }
            System.out.println();
        }
        System.out.println("Обратная по модулю " + ANSI_PINK + alphabet.size() + ANSI_RESET + " матрица ключа:");
        //ЭТО СОЮЗНАЯ МАТРИЦА ПОКА ЧТО!!!!
        int[][] reversedMatrix = transModeMatrix(key, alphabet.size());
        // ОБРАТНЫЙ ЭЛЕМЕНТ ДЕТЕРМИНАНТА В КОЛЬЦЕ ПО МОДУЛЮ ДЛИНЫ АЛФАВИТА
        int reverseDet = revDet(det4(key), alphabet.size());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                reversedMatrix[i][j] = correctRemainder(reversedMatrix[i][j] * reverseDet, alphabet.size());
                //reversedMatrix[i][j] = correctRemainder(reversedMatrix[i][j], alphabet.size());
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(reversedMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Проведем матричное умножение обратной матрицы ключа на каждый вектор-фрагмент фразы и возьмем результат по модулю " +
                ANSI_BRIGHT + alphabet.size() + ANSI_RESET + ":");
        String encodedPhrase = "";
        for (int i = 0; i < hurt.length() / 4; i++) {
            encodedPhrase += mulMatrixMode(reversedMatrix, phraseMatrix[i], 4, alphabet.size(), alphabet);
        }
        System.out.println("В результате дешифрования получим фразу: " + ANSI_BLUE+ encodedPhrase + ANSI_RESET);
        System.out.println("Начальная фраза: " + ANSI_BLUE + truePhrase + ANSI_RESET);
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

        System.out.println("Выберите дальнейшие действия: \n" + ANSI_PINK + "1 -- Расшифровать полученное сообщение;" +
                "\n2 -- Повредить полученное сообщение;\n3 -- Зашифровать фразу другим ключем;" + ANSI_RESET);
        int choice = in.nextInt();
        switch (choice) {
            case 2 -> destroy(alphabet, phrase, encodedPhrase, keyMatrix);
        }
        //System.out.println(Arrays.deepToString(keyMatrix));
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

        System.out.println("Выберите дальнейшие действия: \n" + ANSI_PINK + "1 -- Расшифровать полученное сообщение;" +
                "\n2 -- Повредить полученное сообщение;\n3 -- Зашифровать фразу другим ключем;"  + ANSI_RESET);
        int choice = in.nextInt();
        switch (choice) {
            case 2 -> destroy(alphabet, phrase, encodedPhrase, keyMatrix);
        }
       // System.out.println(Arrays.deepToString(keyMatrix));

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

        System.out.println("Выберите дальнейшие действия: \n" + ANSI_PINK + "1 -- Расшифровать полученное сообщение;" +
                "\n2 -- Повредить полученное сообщение;\n3 -- Зашифровать фразу другим ключем;" + ANSI_RESET);
        int choice = in.nextInt();
        switch (choice) {
            case 2 -> destroy(alphabet, phrase, encodedPhrase, keyMatrix);
        }
       // System.out.println(Arrays.deepToString(keyMatrix));

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
        int ans = ar[0][0] * ar[1][1] * ar[2][2] + ar[0][1] * ar[1][2] * ar[2][0] +
                ar[1][0] * ar[2][1] * ar[0][2] - ar[2][0] * ar[1][1] * ar[0][2] -
                ar[0][0] * ar[2][1] * ar[1][2] - ar[0][1] * ar[1][0] * ar[2][2];
        return ans;
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
