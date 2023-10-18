import java.util.Scanner;


import static java.lang.Math.*;

public class Reflection {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите коэффициент а:");
        double a = in.nextDouble();
        int n = 2;
        double[] old = new double[n];
        double[] newXY = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Введите координату:");
            old[i] = in.nextDouble();
        }
        double[][] matrix = new double[n][n];
        matrix[0][0] = -0.6;
        matrix[0][1] = 0.8;
        matrix[1][0] = 0.8;
        matrix[1][1] = 0.6;

        newXY = mulMatrix(matrix, old);

        System.out.println(round(newXY[0]));
        System.out.println(newXY[1]);
    }

    public static double[] mulMatrix(double[][] m, double[] c) {
        int s = m.length;
        double[] ans = new double[2];

        for (int i = 0; i < s; i++) {
            ans[i] = m[i][0] * c[0] + m[i][1] * c[1];
        }
        return ans;
    }
}
