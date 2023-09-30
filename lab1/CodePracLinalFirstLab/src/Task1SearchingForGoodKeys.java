import java.util.ArrayList;
import java.util.HashMap;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Task1SearchingForGoodKeys {
    public static void main(String[] args) {
        Character[] letters = {'А', 'В', 'Д', 'Ё', 'З', 'Л', 'Н', 'П', 'Ы', 'Ь', 'Я' };
        getForFour(letters);

    }
    public static int[][] minor ( int[][] matrix, int rowNum, int colNum ) {

        final int n = matrix.length - 1;
        final int m = matrix[0].length - 1;

        int[][] result = new int[ n ][ m ];

        for ( int i = 0; i < matrix.length; i++ ) {
            boolean isRowDeleted = rowNum < i;
            int resultRowIndex = isRowDeleted ? i - 1 : i;

            for ( int j = 0; j < matrix[i].length; j++ ) {
                boolean isColDeleted = colNum < j;
                int resultColIndex = isColDeleted ? j - 1 : j;

                if (rowNum != i && colNum != j)
                    result[resultRowIndex][resultColIndex] = matrix[i][j];
            }
        }
        return result;
    }
    public static void getForNine(Character[] letters) {
        int[][] ar = new int[3][3];
        for (int a = 0; a < 11; a++) {
            ar[0][0] = a;
            for (int b = 0; b < 11; b++) {
                ar[0][1] = b;
                for (int c = 0; c < 11; c++) {
                    ar[0][2] = c;
                    for (int d = 0; d < 11; d++) {
                        ar[1][0] = d;
                        for (int e = 0; e < 11; e++) {
                            ar[1][1] = e;
                            for (int f = 0; f < 11; f++) {
                                ar[1][2] = f;
                                for (int g = 0; g < 11; g++) {
                                    ar[2][0] = g;
                                    for (int h = 0; h < 11; h++) {
                                        ar[2][1] = h;
                                        for (int i = 0; i < 11; i++) {
                                            ar[2][2] = i;

                                            int det = ar[0][0] * ar[1][1] * ar[2][2] + ar[0][1] * ar[1][2] * ar[2][0] +
                                                    ar[1][0] * ar[2][1] * ar[0][2] - ar[2][0] * ar[1][1] * ar[0][2] -
                                                    ar[0][0] * ar[2][1] * ar[1][2] - ar[0][1] * ar[1][0] * ar[2][2];

                                            if (det != 0) {
                                                // извините, но дальше будет проверка путем перемножения матриц
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void getForFour( Character[] letters) {
        for (int i = 0; i < 11; i++) {
            int[][] newOne = new int[2][2];
            newOne[0][0] = i;
            for (int j = 0; j < 11; j++) {
                newOne[0][1] = j;
                for (int k = 0; k < 11; k++) {
                    newOne[1][0] = k;
                    for (int l = 0; l < 11; l++) {
                        newOne[1][1] = l;

                        int det = newOne[0][0] * newOne[1][1] - newOne[0][1] * newOne[1][0];

                        if (det != 0) {

                            int a = newOne[1][1];
                            int b = newOne[0][0];
                            int c = -newOne[1][0];
                            int d = -newOne[0][1];
                            if (((a % det) == 0) && ((b % det) == 0) && ((c % det) == 0) && ((d % det) == 0))
                                if((a != b) && (a != -c) && (a != -d) && (b != -c) && (b != -d) && (c != d)) {
                                    for (int m = 0; m < 2; m++) {
                                        for (int n = 0; n < 2; n++)
                                            System.out.print(letters[newOne[m][n]] + " ");
                                        System.out.println();
                                    }
                                    System.out.println("-------");
                                }
                        }
                    }
                }
            }
        }
    }

}
