package task_1;

import java.util.concurrent.atomic.AtomicInteger;

public class evklid {
    public static int extended_gcd(int a, int b, AtomicInteger x, AtomicInteger y)
    {
        if (a == 0)
        {
            x.set(0);
            y.set(1);
            return b;
        }

        AtomicInteger _x = new AtomicInteger(), _y = new AtomicInteger();
        int gcd = extended_gcd(b % a, a, _x, _y);

        x.set(_y.get() - (b/a) * _x.get());
        y.set(_x.get());

        return gcd;
    }

    public static void main(String[] args)
    {
        int a = 379;
        int b = 37;
/*
        AtomicInteger x = new AtomicInteger(), y = new AtomicInteger();

        System.out.printf("x = %d, y = %d\n", x.get(), y.get());

 */
        myEvklyd(a, b);
    }

    public static void myEvklyd(int a, int b) {
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
        if (b != 0) {
            bezout = (oldR - oldS * a) / b;
        } else
            bezout = 0;
        System.out.println(oldS + " " + bezout);
        System.out.println(oldR);
    }

}
