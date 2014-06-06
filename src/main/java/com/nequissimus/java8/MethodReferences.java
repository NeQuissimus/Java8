package com.nequissimus.java8;

import java.util.Arrays;
import java.util.List;

final class MethodReferences {
  public static void main(String[] args) {
    final List<String> l = Arrays.asList(new String[] {"hello", "world", "foo", "bar"});
    l.forEach(System.out::println);
  }
}
