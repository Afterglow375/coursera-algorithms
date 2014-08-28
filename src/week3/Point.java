package week3;

/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import edu.princeton.cs.introcs.StdDraw;

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new myComparator();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    private class myComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            double slope1 = Point.this.slopeTo(p1);
            double slope2 = Point.this.slopeTo(p2);
            if (slope1 < slope2) {
                return -1;
            }
            else if (slope1 == slope2) {
                return 0;
            }
            else
                return 1;
        }
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double xDiff = (that.x - this.x);
        double yDiff = (that.y - this.y);
        if (xDiff == 0 && yDiff == 0) // Negative infinity if the points are the same
            return Double.NEGATIVE_INFINITY;
        else if (xDiff == 0) // 0 if horizontal line
            return 0;
        else if (yDiff == 0) // Positive infinity if vertical line
            return Double.POSITIVE_INFINITY;
        else
            return yDiff/xDiff;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        if (this.y < that.y)
            return -1;
        if (this.y == that.y) {
            if (this.x < that.x)
                return -1;
            if (this.x == that.x)
                return 0;
        }
        return 1;
    }

    // return string representation of this point
    @Override
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(0, 0);
        Point p4 = new Point(6, 5);
        Point p5 = new Point(5, 6);
        System.out.println(p1.compareTo(p2));
        Point[] arr = {p1, p2, p3, p4, p5};
        Arrays.sort(arr, p3.SLOPE_ORDER);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);
    }
}