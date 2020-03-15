package com.selfdev.philosoph;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by vovkv on 5/26/2018.
 */
public class ProperFractions {

    public static long properFractions(long n) {
        // good luck
        int counter = 0;
        boolean isDivBy2 = false;
        boolean isDivBy5 = false;
        if(n%2 == 0) {
            isDivBy2 = true;
        }
        if(n%5 == 0) {
            isDivBy5 = true;
        }

        for(long d = 1; d<=n; d++) {
            if(isDivBy2 && d%2 == 0) { counter++; continue; }
            if(isDivBy5 && d%5 == 0) { counter++; continue; }

            long GCD = recursiveGCD(n, d);
            if(GCD == 1) counter++;

        }
        return counter;
    }

    public static long countGCD(long n, long d) {
        while(n!= 0 && d!=0) {
            if(n > d) {
                n = n%d;
            } else {
                d = d%n;
            }
        }
        return n + d;
    }

    public static long recursiveGCD(long n, long d) {
        if(d == 0) {
            return n;
        }else
            return recursiveGCD(d, n%d);
    }


    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        long result = properFractions(40000000l);
        System.out.println(result);
        System.out.println((System.currentTimeMillis() - startTime)/1000);
    }

}
