package com.nequissimus.java8;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class Lambdas {
  public static void main(String[] args) {
    supply();
    sideEffect();
    function();
    multiParam();
    varIntoLambda();
  }

  /**
  * A function with two parameters
  *
  * f: (x, y) -> z
  */
  static void multiParam() {
    final BiFunction<String, String, String> b = (x, y) -> x + " " + y;
    System.out.println("(x, y) -> z: " + b.apply("hello", "world"));
  }

  /**
  * Generate a value
  *
  * f: () -> x
  */
  static void supply() {
    final Supplier<Integer> s = () -> 42;
    System.out.println("() -> x: " + s.get());
  }

  /**
  * Side-effect with a value
  *
  * f: x -> ()
  */
  static void sideEffect() {
    final Consumer<Integer> c = x -> System.out.println("x -> (): " + x);
    c.accept(42);
  }

  /**
  * Apply a function
  *
  * f: x -> y
  */
  static void function() {
    final Function<Integer, Integer> f = x -> x * 3;
    System.out.println("x -> y: " + f.apply(14));
  }

  /**
  * Only variables that are (effectively) final can be captured by a lambda
  */
  static void varIntoLambda() {
    final Consumer<String> f = x -> System.out.println(x);
    String a = "hello";
    final String b = "foo";

    // a is properly passed into function, no issues / no capture
    f.accept(a);
    f.accept(b);

    // Will not compile, a is not (effectively) final, cannot be captured by lambda definition
    /* final Supplier<String> pa = () -> { System.out.println(a); return a; }; */

    // b is final, so capturing its value is fine
    final Supplier<String> pb = () -> { System.out.println(b); return b; };

    a = "world";

    pb.get();

  }
}
