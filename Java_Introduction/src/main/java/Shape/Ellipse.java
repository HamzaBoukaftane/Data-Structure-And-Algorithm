/*
 * This Java file contains the declaration
 * of the Ellipse class which inherit from
 * BaseShape class.
 * file Ellipse.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     12 february 2023
 * Modified 1 february 2023
 */

package Shape;

import Point.Point2d;

import java.util.Collection;

public class Ellipse extends BaseShape {

    /**
    * This function was added to simplify Ellipse(Double widthDiameter, Double heightDiameter)
    * The math formula is available on the following link:
    * https://www.geeksforgeeks.org/check-if-a-point-is-inside-outside-or-on-the-ellipse/
     */
    protected boolean isInside(Double demiAxisX, Double demiAxisY, Double x, Double y){
        double hRatio = Math.pow(x,2) / Math.pow(demiAxisX,2);
        double vRatio = Math.pow(y,2) / Math.pow(demiAxisY,2);
        double res    = hRatio + vRatio;
        return res <= 1;
    }

    public Ellipse(Double widthDiameter, Double heightDiameter) {

        super();
        double xInterval = widthDiameter/2;
        double yInterval = heightDiameter/2;
        double incr      = 0.5;

        for (double x = -xInterval; x <= xInterval; x+=incr)
            for (double y = -yInterval; y <= yInterval; y+=incr)
                if (isInside(xInterval,yInterval,x,y))
                    this.add(new Point2d(x,y));
    }

    public Ellipse(Point2d dimensions) {
        this(dimensions.X(), dimensions.Y());
    }

    private Ellipse(Collection<Point2d> coords) {
        this.addAll(coords);
    }

    @Override
    public Ellipse translate(Point2d point) {
        super.translate(point);
        return this;
    }

    @Override
    public Ellipse rotate(Double angle) {
        super.rotate(angle);
        return this;
    }

    @Override
    public Ellipse clone() {
        return new Ellipse(this.cloneCoords());
    }
}
