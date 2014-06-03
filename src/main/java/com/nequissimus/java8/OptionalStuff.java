package com.nequissimus.java8;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

final class OptionalStuff {
  static int expensiveCall() {
    try {
      Thread.sleep(10_000);
    } catch (InterruptedException e) {}

    return 0;
  }

  /**
  * "Expensive" call to an API, used for {Optional#orElseGet}
  */
  static class Fallback implements Supplier<Integer> {
    public Integer get() {
      return expensiveCall();
    }
  }

  /**
  * Function that may return a String or null
  */
  static String randomString() {
    if (System.currentTimeMillis() % 2 == 0) {
      return null;
    } else {
      return "lasjlkasjdlas";
    }
  }

  public static void main(String[] args) {
    final Optional<String> oStr = Optional.ofNullable(randomString());
    final Optional<Integer> oLen = oStr.map(x -> x.length());

    System.out.println("Length = " + oLen.orElseGet(new Fallback()));
  }

  /**
  * Pre-Java 8 with try/catch
  */
  public static void oldWayTry() throws Exception {
    final String r = randomString();
    int l;

    try {
      l = r.length();
    } catch (Exception e) {
      l = expensiveCall();
    }

    System.out.println("Length = " + l);
  }

  /**
  * Initialize to "null"-value, then replace with proper value
  */
  public static void oldWayInit() {
    final String r = randomString();
    int l = expensiveCall();

    if (r != null) {
      l = r.length();
    }

    System.out.println("Length = " + l);
  }

  /**
  * Pre-Java 8 with a guard
  */
  public static void oldWayGuard() {
    final String r = randomString();
    int l;

    if (r != null) {
      l = r.length();
    } else {
      l = expensiveCall();
    }

    System.out.println("Length = " + l);
  }
}
