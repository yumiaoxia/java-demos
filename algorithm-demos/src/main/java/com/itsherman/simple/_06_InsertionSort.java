package com.itsherman.simple;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class _06_InsertionSort {

    public static void insertSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            int temp = nums[i];
            for (; j >= 0; j--) {
                if (nums[j] > temp) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
                nums[j] = temp;
            }
            //  System.out.println("第" + i + "躺结束后,排序数组为： " + Arrays.toString(nums));
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
        insertSort(nums);
        System.out.println(String.format("数据长度大小为%s,排序耗时%s", size, Duration.between(start, Instant.now()).toMillis()));
        System.out.println(Arrays.toString(nums));
    }
}
