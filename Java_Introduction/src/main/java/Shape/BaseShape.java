/*
 * This Java file contains the declaration
 * of the BaseShape class which contains different
 * types of methods and collection of Points.
 * file BaseShape.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     12 february 2023
 * Modified 1 february 2023
 */

package Shape;

import Point.Point2d;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape implements Cloneable {
    private final Collection<Point2d> coords;

    public BaseShape() {
        this.coords = new ArrayList<Point2d>();
    }

    public BaseShape(Collection<Point2d> coords) {
        this();
        this.addAll(coords);
    }

    public BaseShape add(Point2d coord) {
        coords.add(coord.clone());
        return this;
    }

    public BaseShape add(BaseShape shape) {
        return this.addAll(shape.getCoords());
    }

    public BaseShape addAll(Collection<Point2d> coords) {
        coords.forEach(this::add);
        return this;
    }

    public BaseShape remove(Point2d coord) {
        coords.remove(coord);
        return this;
    }

    public BaseShape remove(BaseShape shape) {
        this.removeAll(shape.getCoords());
        return this;
    }

    public BaseShape removeAll(Collection<Point2d> coords) {
        coords.forEach(this::remove);
        return this;
    }

    public Collection<Point2d> getCoords() {
        return new ArrayList<>(coords);
    }

    public Collection<Point2d> cloneCoords() {
        ArrayList<Point2d> copy = new ArrayList<>();
        coords.forEach(point->copy.add(point.clone()));
        return copy;
    }

    public BaseShape translate(Point2d translateVector) {
        coords.forEach(point->point.translate(translateVector));
        return this;
    }

    public BaseShape rotate(Double angle) {
        coords.forEach(point->point.rotate(angle));
        return this;
    }

    public Double getMaxX() {
        return coords.stream().mapToDouble(Point2d::X)
                                    .max()
                                    .orElse(-Double.MAX_VALUE);
    }

    public Double getMaxY() {
        return coords.stream().mapToDouble(Point2d::Y)
                                    .max()
                                    .orElse(-Double.MAX_VALUE);
    }

    public Point2d getMaxCoord() {
        return new Point2d(getMaxX(),getMaxY());
    }

    public Double getMinX() {
        return coords.stream().mapToDouble(Point2d::X)
                                    .min()
                                    .orElse(Double.MAX_VALUE);
    }

    public Double getMinY() {
        return coords.stream().mapToDouble(Point2d::Y)
                                    .min()
                                    .orElse(Double.MAX_VALUE);
    }

    public Point2d getMinCoord() {
        return new Point2d(getMinX(),getMinY());
    }

    public BaseShape clone() {
        return new BaseShape(this.coords);
    }
}
