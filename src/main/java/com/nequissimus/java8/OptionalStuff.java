package com.nequissimus.java8;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

final class OptionalStuff {
  /**
  * "Expensive" call to an API, used for {Optional#orElseGet}
  */
  static class Fallback implements Supplier<Integer> {
    public Integer get() {
      try {
        Thread.sleep(10_000);
      } catch (InterruptedException e) {}

      return 0;
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
    final Function<String, Optional<Integer>> len = x -> Optional.of(x.length());

    final Optional<String> oStr = Optional.ofNullable(randomString());
    final Optional<Integer> oLen = oStr.flatMap(len);

    System.out.println("Length = " + oLen.orElseGet(new Fallback()));
  }
}
