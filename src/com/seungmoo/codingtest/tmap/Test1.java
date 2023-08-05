package com.seungmoo.codingtest.tmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {

        int[] A = {55};

        Map<Integer, Integer> step1 = new HashMap<>();
        Map<Integer, Integer> subtract1 = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int element = A[i];

            int digitSum = getDigitSum(element);
            step1.put(i, digitSum);
            subtract1.put(i, element - digitSum);
        }

        Map<Integer, Integer> step2 = new HashMap<>();
        Map<Integer, Integer> subtract2 = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int element = A[i];

            int digitSum = getDigitSum(element);

            int secondDigitSum = getDigitSum(digitSum);

            step2.put(i, secondDigitSum);
            subtract2.put(i, element - secondDigitSum);
        }

        if (A.length == 1) {
            System.out.println(getStep2Sum(A, step2, subtract2));
            return;
        }

        int step1Sum = getStep1Sum(A, step1, subtract1);
        int step2Sum = getStep2Sum(A, step2, subtract2);

        int result = Math.min(step1Sum, step2Sum);

        System.out.println(result);
    }

    private static int getStep1Sum(int[] param, Map<Integer, Integer> step1, Map<Integer, Integer> subtract1) {
        List<Map.Entry<Integer, Integer>> sortedSubtract1 = subtract1.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        int step1Target1Index = sortedSubtract1.get(param.length - 1).getKey();
        int step1Target2Index = sortedSubtract1.get(param.length - 2).getKey();

        int step1Sum = 0;
        for (int i = 0; i < param.length; i++) {
            if (step1Target1Index == i || step1Target2Index == i) {
                step1Sum += step1.get(i);
            } else {
                step1Sum += param[i];
            }
        }
        return step1Sum;
    }

    private static int getStep2Sum(int[] param, Map<Integer, Integer> step2, Map<Integer, Integer> subtract2) {
        List<Map.Entry<Integer, Integer>> sortedSubtract2 = subtract2.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        int step2Target1Index = sortedSubtract2.get(param.length - 1).getKey();

        int step2Sum = 0;
        for (int i = 0; i < param.length; i++) {
            if (step2Target1Index == i) {
                step2Sum += step2.get(i);
            } else {
                step2Sum += param[i];
            }
        }
        return step2Sum;
    }

    private static int getDigitSum(int element) {
        int sum = 0;

        while (element >= 10) {
            sum += element % 10;
            element = element / 10;
        }

        sum += element;

        return sum;
    }

}
