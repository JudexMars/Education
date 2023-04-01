package Tinkoff.Eternal_Contest.Task5;

import java.util.Scanner;
@SuppressWarnings("all")
public class Alt {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            long l = in.nextLong();
            long r = in.nextLong();

            boolean sameL = allSame(l);
            boolean sameR = allSame(r);

            long res = 0;

            //if (sameL && l % 10 != 1) res++;
            //if (sameR && r % 10 != 9) res++;


            System.out.println(res);
            double logDiff = Math.floor(Math.log10(r / l)) * 9;
            System.out.println("logDiff = " + logDiff);
            
            if (logDiff == 0) logDiff = 9;
            res += logDiff;
            long extraBottom = l / ((int)(Math.pow(10,(int)Math.log10(l)))) - 1;
        
            System.out.println("ExtraBottom: " + extraBottom);
            long extraUp = 9 - r / ((int)(Math.pow(10,(int)Math.log10(r))));
            if (logDiff != 9 && logDiff <= 18 && r > 9 && ("" + r).charAt(1) == '0') extraUp = 0; 
            System.out.println("ExtraUp: " + extraUp);
            res -= (extraBottom + extraUp);
        

            System.out.println(res);
        }
    }

    private static boolean allSame(long x) {
        String s = "" + x;
        System.out.println("Observing " + s);
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i) + " ? " + s.charAt(0));
            if (s.charAt(0) != s.charAt(i)) {
                System.out.println(s + " is not the same");
                return false;
            }
        }
        System.out.println(s + " is the same");
        return true;
    }
}
