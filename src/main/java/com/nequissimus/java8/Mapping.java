package com.nequissimus.java8;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class Mapping {
  public static void main(String[] args) {
    final List<Integer> l = Arrays.asList(new Integer[] { 2, 4, 6, 8, 10 });

    // Map via lambda
    final List<Integer> l1 = l.stream().map(x -> x * 10).collect(Collectors.toList());

    // Multiply values and add to new list
    final List<Integer> l2 = new ArrayList<>();
    for (Integer i : l) {
      l2.add(i * 10);
    }

    l1.forEach(System.out::println);
    l2.forEach(System.out::println);
  }
}
