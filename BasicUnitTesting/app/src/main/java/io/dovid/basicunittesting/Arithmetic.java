package io.dovid.basicunittesting;

/**
 * Created by umbertodovidio on 08/08/17.
 */

public class Arithmetic {

    public static long factorial(int n) {
        if (n > 20 ) {
            throw new ArithmeticException("Number is too large to compute");
        }
        if (n < 0) {
            throw new ArithmeticException("Factorial is not defined for negative numbers");
        }
        if (n == 0) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
}
