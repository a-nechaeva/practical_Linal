package task_1;

import static task_1.Encryption.correctRemainder;

public class evklid {
    public static void main(String[] args)
    {
        int a = -1;
        int b = 11;
        //System.out.println((16 * (-7)) % 31 + 31);
        System.out.println(extEvklyd(a, b));
    }

    public static int extEvklyd(int a, int b) {
        int detMode = correctRemainder(a, b);
        int newMode = detMode;
        int n = 1;

        while ((newMode * n % b) != 1) {
            n++;
        }
        return n;


        /*
        int s = 0;
        int oldS = 1;
        int r = b;
        int oldR = a;
        int bezout;
        while (r != 0) {
            int qu = oldR / r;
            int t = oldR;
            oldR = r;
            r = t - qu * r;
            int t2 = oldS;
            oldS = s;
            s = t2 - qu * s;
        }

        if (b != 0) {
            bezout = (oldR - oldS * a) / b;
        } else
            bezout = 0;
        return oldS;
        //return bezout;

         */

    }

}
