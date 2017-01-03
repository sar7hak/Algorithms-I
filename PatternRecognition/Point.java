import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
  private int x;
  private int y;



  public Point(int x, int y) {
  	this.x = x;
  	this.y = y;
  }                        // constructs the point (x, y)

  public void draw() {
  	StdDraw.point(x,y);
  }                              // draws this point

  public void drawTo(Point that) {
  	StdDraw.line(this.x,this.y,that.x,that.y);
  }                  // draws the line segment from this point to that point

  public String toString() {
  	return "("+x+","+y+")";
  }                          // string representation


  public int compareTo(Point that) {
  	if (that.y < y)
  	  return 1;
  	else if (that.y > y)
  	  return -1;
  	else if (that.x < x)
  	  return 1;
  	else if (that.x > x)
  	  return -1;
  	else
  	  return 0;
  }    // compare two points by y-coordinates, breaking ties by x-coordinates

  public double slopeTo(Point that) {
  	if (x == that.x && that.y == y)
  	  return Double.NEGATIVE_INFINITY;
  	if (x == that.x)
  	  return Double.POSITIVE_INFINITY;
  	if (y == that.y)
  	  return 0.0;
  	else
  	  return (that.y - y)/((double) (that.x - x));
  }      // the slope between this point and that point

  private class bySlope implements Comparator<Point> {
  	public int compare(Point a, Point b) {
  		//Point p = new Point(x,y);
      double slopeA = slopeTo(a);
      double slopeB = slopeTo(b);
  		if (slopeA > slopeB)
  		  return 1;
  		else if (slopeA == slopeB)
  		  return 0;
  		else
  		  return -1;
  	}
  }

  public Comparator<Point> slopeOrder() {
  	return new bySlope();
  }             // compare two points by slopes they make with this point

  public static void main(String[] args) {
  	Point p = new Point(167,432);
  	Point q = new Point(167,217);
  	Point r = new Point(167,444);
  	System.out.println(p.slopeTo(q));
  	System.out.println(p.slopeTo(q) == p.slopeTo(r));
  }
}