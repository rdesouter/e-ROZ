package com.technocite.ERos.utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PerfomanceOfRandom {


    public static Set<Integer> findDuplicateInArray(int...array){
        Set<Integer> unique1 = new HashSet<>();
        Set<Integer> duplicate1 = new HashSet<>();
        for (int val : array){
            (unique1.contains(val) ? duplicate1 : unique1).add(val);
        }
        return duplicate1;
    }

    public static int[] getArrayOfRandomIntWithoutSecure(int arraySize, int maxBound){
        int[] array = new int[arraySize];
        Random random = new Random();
//      System.out.println("nombre aléatoire :" + random.nextDouble());
        for (int i =0; i <arraySize ; i++){
            int randomInt = random.nextInt(maxBound);
//          System.out.println("nombre aléatoire généré :" + randomInt);
            array[i]= randomInt;
        }
        return array;
    }

    public static int[] getArrayOfRandomIntWithSecure(int arraySize, int maxBound){
        int[] array = new int[arraySize];
        SecureRandom secureRandom = new SecureRandom();
//      System.out.println("nombre aléatoire avec SecureRandom = " + secureRandom);
        for (int i =0; i <arraySize ; i++){
            int randomInt = secureRandom.nextInt(maxBound);
//          System.out.println("nombre aléatoire généré :" + randomInt);
            array[i]= randomInt;
        }
        return array;
    }
}
