/*
 * This Java file contains the declaration
 * of the point class which contains different
 * types of methods.
 * file PointOperateur.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     12 february 2023
 * Modified 1 february 2023
 */

package Point;

import java.util.*;
import java.util.stream.IntStream;

public final class PointOperator {

    public static void translate(Double[] vector, Double[] translateVector) {
        IntStream.range(0, vector.length).forEach(i-> vector[i] += translateVector[i]);
    }

    public static void rotate(Double[] vector, Double[][] rotationMatrix) {
        int len = vector.length;
        Double[] res = new Double[len];
        Arrays.fill(res, 0.0);
        IntStream.range(0, len)
                 .forEach(i -> {
                    IntStream.range(0, len)
                             .forEach(j -> res[i] += rotationMatrix[i][j] * vector[j]);
                });
        IntStream.range(0, len)
                 .forEach(i -> vector[i] = res[i]);
    }

    public static void divide(Double[] vector, Double divider) {
        IntStream.range(0, vector.length).forEach(i -> vector[i] /= divider);
    }

    public static void multiply(Double[] vector, Double multiplier) {
        IntStream.range(0, vector.length).forEach(i -> vector[i] *= multiplier);
    }

    public static void add(Double[] vector, Double adder) {
        IntStream.range(0, vector.length).forEach(i -> vector[i] += adder);
    }
}
