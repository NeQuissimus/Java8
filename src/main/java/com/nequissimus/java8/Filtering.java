package com.nequissimus.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

final class Filtering {
  public static void main(String[] args) {
    final Predicate<String> notNull = x -> x != null;
    final Predicate<String> p = x -> x.length() > 2;
    final List<String> base = Arrays.asList(new String[] {"foo", "si", "bar"});

    // Make a new list with matching elements
    System.out.println(newList(base, p));

    // Manually remove unwanted elements from list
    final List<String> copy = new ArrayList<>(base);
    inPlace(copy, p);
    System.out.println(copy);

    // Remove unwanted elements from list
    final List<String> copy2 = new ArrayList<>(base);
    predRemove(copy2, p);
    System.out.println(copy2);

    // Make a new list with matching elements by using Stream.filter
    System.out.println(predFilterStream(base, p));

    /** Add null to the list of values (all previous examples will fail with a NullPointerException) **/
    final List<String> baseNull = new ArrayList<>(base);
    baseNull.add(null);

    System.out.println(predFilterStream(baseNull, notNull.and(p)));
  }

  /**
  * Convert list to stream and filter
  */
  static List<String> predFilterStream(List<String> l, Predicate<String> p) {
    return l.stream().filter(p).collect(Collectors.toList());
  }

  /**
  * Use predicate to remove unwanted elements in-place
  */
  static void predRemove(List<String> l, Predicate<String> p) {
    final Predicate<String> inverse = p.negate();
    l.removeIf(inverse);
  }

  /**
  * Manually remove elements in-place
  */
  static void inPlace(List<String> l, Predicate<String> p) {
    final List<String> tmp = new ArrayList<String>();
    final Predicate<String> pInverse = p.negate();

    for (String s : l) {
      if (pInverse.test(s)) {
        tmp.add(s);
      }
    }

    l.removeAll(tmp);
  }

  /**
  * Create new list with matching elements
  */
  static List<String> newList(List<String> l, Predicate<String> p) {
    final List<String> tmp = new ArrayList<String>();

    for (String s : l) {
      if (p.test(s)) {
        tmp.add(s);
      }
    }

    return tmp;
  }
}
