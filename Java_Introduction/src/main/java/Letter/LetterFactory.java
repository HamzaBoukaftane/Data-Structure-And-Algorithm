package Letter;

import Point.Point2d;
import Shape.*;

import java.util.ArrayList;

public final class LetterFactory {
    final static Double maxHeight = 150.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;
    final static Double angleDiagonal_A_V = Math.toRadians(11);
    final static Double angleDiagonal_N_M = Math.toRadians(22);


    private static Rectangle maxVLine(){
        return new Rectangle(halfStripeThickness,maxHeight);
    }

    private static Rectangle maxHLine(){
        return new Rectangle(maxWidth,halfStripeThickness);
    }

    private static BaseShape halfVLine(){
        return new Rectangle(halfStripeThickness,halfMaxHeight);
    }

    private static BaseShape halfHLine(){
        return new Rectangle(halfMaxWidth,halfStripeThickness);
    }

    private static Point2d up(){
        return new Point2d(0.0,-halfMaxHeight+halfStripeThickness/2);
    }

    private static Point2d down(){
        return new Point2d(0.0,halfMaxHeight-halfStripeThickness/2);
    }

    private static Point2d halfUp(){
        return up().divide(2.0);
    }

    private static Point2d left(){
        return new Point2d(-halfMaxWidth,0.0);
    }

    private static Point2d halfLeft(){
        return left().divide(2.0);
    }

    private static Point2d right(){
        return new Point2d(halfMaxWidth,0.0);
    }

    private static Point2d halfRight(){
        return right().divide(2.0);
    }

    public static BaseShape create_T() {
        BaseShape hLine = maxHLine().translate(up());
        return maxVLine().add(hLine);
    }

    public static BaseShape create_E() {
        BaseShape vLine = maxVLine().translate(left());
        BaseShape hLineUp = maxHLine().translate(up()).add(vLine);
        BaseShape hLineDown = maxHLine().translate(down()).add(hLineUp);
        return maxHLine().add(hLineDown);
    }

    public static BaseShape create_O() {
        BaseShape innerLine = new Ellipse (
                maxWidth-stripeThickness,
                maxHeight-stripeThickness);
        return new Ellipse(maxWidth,maxHeight).remove(innerLine);
    }

    public static BaseShape create_C() {
        return create_O().remove(
                new Square(maxHeight*2/3).translate(right().multiply(2.0))
        );
    }

    public static BaseShape create_A() {
        BaseShape diagonalRight = maxVLine().rotate(-angleDiagonal_A_V)
                                            .translate(halfRight());
        BaseShape diagonalLeft = maxVLine().rotate(angleDiagonal_A_V)
                                           .translate(halfLeft())
                                           .add(diagonalRight);
        return halfHLine().add(diagonalLeft);
    }

    public static BaseShape create_V() {
        BaseShape diagonalRight = maxVLine().rotate(angleDiagonal_A_V)
                                            .translate(halfRight());
        return maxVLine().rotate(-angleDiagonal_A_V)
                         .translate(halfLeft())
                         .add(diagonalRight);
    }

    public static BaseShape create_N() {
        BaseShape vLineLeft = maxVLine().translate(left());
        BaseShape vLineRight = maxVLine().translate(right()).add(vLineLeft);
        return maxVLine().rotate(-angleDiagonal_N_M).add(vLineRight);
    }

    public static BaseShape create_M() {
        BaseShape diagonalRight = halfVLine().rotate(angleDiagonal_N_M)
                                             .translate(halfRight());
        BaseShape diagonalLeft = halfVLine().rotate(-angleDiagonal_N_M)
                                            .translate(halfLeft())
                                            .add(diagonalRight)
                                            .translate(halfUp());
        BaseShape vLineLeft = maxVLine().translate(left()).add(diagonalLeft);
        return maxVLine().translate(right()).add(vLineLeft);
    }

}
