/*
 * This Java file contains the declaration
 * of the Rectangle class which inherit from
 * BaseShape class.
 * file Rectangle.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     12 february 2023
 * Modified 1 february 2023
 */

package Shape;

import Point.Point2d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

public class Rectangle extends BaseShape {

    public Rectangle(Double width, Double height) {
        double xInterval = width/2;
        double yInterval = height/2;
        double incr       = 0.5;

        for (double x = -xInterval; x <= xInterval; x+=incr)
            for (double y = -yInterval; y <= yInterval; y+=incr)
                this.add(new Point2d(x,y));
    }

    public Rectangle(Point2d dimensions) {
        this(dimensions.X(), dimensions.Y());
    }

    private Rectangle(Collection<Point2d> coords) {
        this.addAll(coords);
    }

    @Override
    public Rectangle translate(Point2d point) {
        super.translate(point);
        return this;
    }

    @Override
    public Rectangle rotate(Double angle) {
        super.rotate(angle);
        return this;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.cloneCoords());
    }
}
