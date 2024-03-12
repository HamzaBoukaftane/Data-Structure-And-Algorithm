/*
 * This Java file contains the declaration
 * of the Circle class which inherit from
 * Ellipse class.
 * file Circle.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     12 february 2023
 * Modified 1 february 2023
 */

package Shape;

public class Circle extends Ellipse {

    public Circle(Double radius) {
        super(radius, radius);
    }
}
