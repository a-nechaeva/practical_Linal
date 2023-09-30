package task_1;

import java.util.concurrent.atomic.AtomicInteger;

public class evklid {

    public static void main(String[] args)
    {
        int a = 379;
        int b = 37;
        //extEvklyd(a, b);
    }

    public static int extEvklyd(int a, int b) {
        int s = 0;
        int r = b;
        int oldS = 1;
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
        /*
        if (b != 0) {
            bezout = (oldR - oldS * a) / b;
        } else
            bezout = 0;

         */

        return oldS;
    }

}
