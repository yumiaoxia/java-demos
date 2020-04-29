package com.itsherman.simple;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序一：
 * 00
 */
public class _01_BubbleSort {

    public static int[] bubbleSort(int[] nums) {
        if (nums != null && nums.length > 1) {
            boolean flag = false;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    nums[i] = nums[i] ^ nums[i + 1];
                    nums[i + 1] = nums[i + 1] ^ nums[i];
                    nums[i] = nums[i] ^ nums[i + 1];
                    flag = true;
                }
            }
            if (flag) {
                nums = bubbleSort(nums);
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int size = 10000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = new Random().nextInt(9999);
        }
        Instant start = Instant.now();
        nums = bubbleSort(nums);
        System.out.println(String.format("数据长度大小为%s,排序耗时%s", size, Duration.between(start, Instant.now()).toMillis()));
        System.out.println(Arrays.toString(nums));

    }
}
