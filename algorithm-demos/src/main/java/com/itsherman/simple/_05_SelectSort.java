package com.itsherman.simple;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class _05_SelectSort {

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex && arr[i] > arr[minIndex]) {
                arr[i] = arr[i] ^ arr[minIndex];
                arr[minIndex] = arr[minIndex] ^ arr[i];
                arr[i] = arr[i] ^ arr[minIndex];
            }
        }
    }

    public static void main(String[] args) {
        int size = 10000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = new Random().nextInt(9999);
        }
        System.out.println(Arrays.toString(nums));
        Instant start = Instant.now();
        selectSort(nums);
        System.out.println(String.format("数据长度大小为%s,排序耗时%s", size, Duration.between(start, Instant.now()).toMillis()));
        System.out.println(Arrays.toString(nums));
    }
}
