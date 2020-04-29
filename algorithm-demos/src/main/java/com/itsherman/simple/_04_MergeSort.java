package com.itsherman.simple;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 * 分治 + 合并
 */
public class _04_MergeSort {

    public static void sort(int[] arr, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            sort(arr, start, middle);
            sort(arr, middle + 1, end);
            merge(arr, start, middle, end);
        }
    }


    private static void merge(int[] arr, int low, int mid, int height) {
        int[] temp = new int[height - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i < mid && j < height) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= height) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[x + low] = temp[x];
        }
    }

    public static void main(String[] args) {
        int size = 100000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = new Random().nextInt(9999);
        }
        System.out.println(Arrays.toString(nums));
        Instant start = Instant.now();
        sort(nums, 0, nums.length - 1);
        System.out.println(String.format("数据长度大小为%s,排序耗时%s", size, Duration.between(start, Instant.now()).toMillis()));
        System.out.println(Arrays.toString(nums));
    }
}
