package com.itsherman.simple;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *  1. 判断原始序列是否大于1，大于才做排序算法，否则直接返回原序列
 *  2. 选出序列第一个值为枢纽元
 *  3. 从第二个数开始与枢纽元比较，小于或者等于放在左边的序列，大于的在有序列
 *  4. 将第二步的左序列和右序列重复第二步骤
 *  5. 将左右序列的结果和枢纽元合并，注意枢纽元放在列列中间
 *
 *
 *
 * */
public class _02_MyQuickSort {


    public static List<Integer> mergeSort(List<Integer> originNumbers) {
        List<Integer> result = originNumbers;
        if (originNumbers != null && originNumbers.size() > 1) {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            int average = originNumbers.get(0);
            for (int i = 1; i < originNumbers.size(); i++) {
                int tem = originNumbers.get(i);
                if (tem <= average) {
                    left.add(tem);
                } else {
                    right.add(tem);
                }
            }
            left = mergeSort(left);
            right = mergeSort(right);
            left.add(average);
            left.addAll(right);
            result = left;
        }
        return result;

    }


    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            nums.add(new Random().nextInt(9999));
        }
        Instant start = Instant.now();
        nums = mergeSort(nums);
        System.out.println(String.format("数据长度大小为%s,排序耗时%s", size, Duration.between(start, Instant.now()).toMillis()));
        System.out.println(nums.toString());
    }
}
