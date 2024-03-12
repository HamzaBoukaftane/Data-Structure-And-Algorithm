/*
 * This Java file contains the declaration
 * of the Point2d class which contains different
 * types of methods and x,y coordinates.
 * file Point2d.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     12 february 2023
 * Modified 1 february 2023
 */

package Point;

public class Point2d extends AbstractPoint {
    private final Integer X = 0;
    private final Integer Y = 1;

    public Point2d(Double x, Double y) {
        super(new double[] {x, y});
    }

    public Point2d(Double[] vector) {
        this(vector[0], vector[1]);
    }

    public double X() {
        return vector[X];
    }

    public double Y() {
        return vector[Y];
    }

    @Override
    public Point2d translate(Double[] translateVector) {
        PointOperator.translate(this.vector,translateVector);
        return this;
    }

    public Point2d translate(Point2d translateVector) {
        return translate(translateVector.vector);
    }

    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        PointOperator.rotate(this.vector,rotationMatrix);
        return this;
    }

    public Point2d rotate(Double angle) {
        Double[][] rotationMatrix = {
                { Math.cos(angle), - Math.sin(angle)},
                { Math.sin(angle),   Math.cos(angle) }
        };
        PointOperator.rotate(this.vector,rotationMatrix);
        return this;
    }

    @Override
    public Point2d divide(Double divider) {
        PointOperator.divide(this.vector,divider);
        return this;
    }

    @Override
    public Point2d multiply(Double multiplier) {
        PointOperator.multiply(this.vector,multiplier);
        return this;
    }

    @Override
    public Point2d add(Double adder) {
        PointOperator.add(this.vector,adder);
        return this;
    }

    @Override
    public Point2d clone() {
        return new Point2d(this.vector);
    }
}
