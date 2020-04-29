package com.itsherman.simple;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序，移动指针法
 */
public class _03_QuickSort {

    public static void quickSort(int[] arr, int low, int height) {
        if (low > height) {
            return;
        }
        int i = low;
        int j = height;
        int flag = arr[low];
        while (i < j) {
            while (i < j && arr[i] <= flag) {
                i++;
            }

            while (i < j && arr[j] > flag) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[i] ^ arr[j];
                arr[j] = arr[j] ^ arr[i];
                arr[i] = arr[i] ^ arr[j];
            }
        }
        if (arr[low] > arr[i]) {
            arr[low] = arr[i];
            arr[i] = flag;

        }
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, height);
    }


    public static void main(String[] args) {
        int size = 10000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = new Random().nextInt(9999);
        }
        System.out.println(Arrays.toString(nums));
        Instant start = Instant.now();
        quickSort(nums, 0, nums.length - 1);
        System.out.println(String.format("数据长度大小为%s,排序耗时%s", size, Duration.between(start, Instant.now()).toMillis()));
        System.out.println(Arrays.toString(nums));
    }
}
