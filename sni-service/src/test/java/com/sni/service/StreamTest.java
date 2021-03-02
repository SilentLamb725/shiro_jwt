package com.sni.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
//        List<Double> arrayList = Arrays.asList(2.5,6.1,5.3,21.7,31.0);
//        arrayList = arrayList.stream().filter(num -> num > 10).collect(Collectors.toList());
//        List<Long> aa = arrayList.stream().map(Math::round).collect(Collectors.toList());
//        aa.forEach(System.out::println);
//        System.out.println(2%11);
        int[][] array = {{7,1},{4,4},{7,0},{5,0},{6,1},{5,2}};
        array = reconstructQueue(array);
        Arrays.stream(array).forEach(aa -> Arrays.stream(aa).forEach(System.out::println));
    }

    public static int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (o1, o2) -> {
            if ((o1[0] + o1[1]) - (o2[0] + o2[1]) != 0) {
                return (o1[0] + o1[1]) - (o2[0] + o2[1]);
            } else {
                if (o1[1] - o2[1] > 0) {

                }
                return o1[1] - o2[1];
            }
        });

        return people;
    }
}
