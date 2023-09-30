package task_1;

import java.util.Scanner;

import static task_1.Encryption.*;

public class tst {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] minor = new int[n - 1][n - 1];
                int row = 0;
                for (int k = 0; k < n; k++) {
                    int col = 0;
                    if(k != i) {
                        for (int l = 0; l < n; l++) {
                            if ((l != j)) {
                                minor[row][col] = matrix[k][l];
                                ++col;
                            }
                        }
                        ++row;
                    }
                }
                if (((i + j) % 2) == 0)
                    answer[i][j] = det3(minor);
                else
                    answer[i][j] = -det3(minor);
            }
        }
        int det = det4(matrix);
        System.out.println(det);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = answer[i][j];
                int t2 = answer[j][i];

                answer[i][j] = t2;
                answer[j][i] = t ;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

    }
}
