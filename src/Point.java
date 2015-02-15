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

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new slopeOrder();       // YOUR DEFINITION HERE
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class slopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
        	Double S1, S2;
        	S1 = Point.this.slopeTo(p1);
        	S2 = Point.this.slopeTo(p2);
        	if(S1 < S2)
        		return -1;
        	else if(S1 == S2)
        		return 0;
        	else
        		return 1;
        }
    }
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
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
        if(this.x == that.x)
        	if(this.y == that.y)
        		return Double.NEGATIVE_INFINITY;
        	else
        		return Double.POSITIVE_INFINITY;
        else if(this.y == that.y)
        	return 0.0;
        else
        	return ((double)(that.y - this.y))/((double) (that.x-this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	if(this.y < that.y)
    		return -1;
    	else if(this.y > that.y)
    		return 1;
    	else if(this.y == that.y && this.x < that.x)
    		return -1;
    	else if(this.y == that.y && this.x > that.x)
    		return 1;
    	else
    		return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point P1 = new Point(50,50);
    	Point P2 = new Point(80,80);
    	Point P3 = new Point(50,60);
    	Point P4 = new Point(80,95);
    	StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);

        StdDraw.setPenColor(StdDraw.BLUE);
        P1.draw();
        P2.draw();
        P3.draw();
        P4.draw();
    	P1.drawTo(P2);
    	P3.drawTo(P4);
    	StdDraw.show();
    	
    	
    }
}
