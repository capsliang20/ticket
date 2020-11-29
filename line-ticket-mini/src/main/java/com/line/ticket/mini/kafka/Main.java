package com.line.ticket.mini.kafka;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(handle(new int[]{-1,1,1,2,3,4,5}));
    }

    static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    static void sort(int[] array) {
        if (array.length == 0) {
            return;
        }
        handle(array, 0);
    }

    static void handle(int[] array, int s) {
        if (s == array.length) {
            System.out.println(Arrays.toString(array));
        }
        for (int i = s; i < array.length; i++) {
            swap(array, s, i);
            handle(array, s + 1);
            swap(array, s, i);
        }
    }

    static int handle(int[] array) {
        int length = array.length;
        if (length < 2)
            return length;
        int left = 0, right = array.length - 1;
        int count = 0;
        while (array[left] == array[left + 1])
            left++;
        while (array[right] == array[right - 1])
            right--;
        while (true) {
            int leftVal = Math.abs(array[left]);
            int rightVal = Math.abs(array[right]);
            if (leftVal == rightVal) {
                left++;
                right--;
            } else if (leftVal < rightVal) {
                right--;
            } else {
                left++;
            }
            count++;
            while (left < length-1 && array[left] == array[left + 1])
                left++;
            while (right > 1 && array[right] == array[right - 1])
                right--;
            if (left > right) {
                break;
            }
        }
        return count;
    }

}
