import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
  private Point[] points;
  private LineSegment[] lines;

  public BruteCollinearPoints(Point[] points) {
  	for (int i = 0; i < points.length; i++) {
  	  if (points[i] == null)
  	  	throw new java.lang.NullPointerException("array has null elements");
  	  for (int j = i+1; j < points.length; j++) 
  	  	if (points[i].compareTo(points[j]) == 0)
  	  	  throw new java.lang.IllegalArgumentException("Points should be distinct");
  	}
    this.points = new Point[points.length];
    for (int i = 0; i < points.length; i++)
  	 this.points[i] = points[i];
    this.lines = updateList();
  	//this.lines = new LineSegment[0];
  }   // finds all line segments containing 4 points

  public int numberOfSegments() {
  	return lines.length;
  }       // the number of line segments

  public LineSegment[] segments() {
    return lines;
  }

  private boolean inLine(Point a, Point b, Point c, Point d) {
    //System.out.println(a.slopeTo(b)+" "+a.slopeTo(c));
  	return a.slopeTo(b) == a.slopeTo(c) && a.slopeTo(c) == a.slopeTo(d);
  }

  private LineSegment line(Point a, Point b, Point c, Point d) {
    Point[] pt = {a,b,c,d};
    Arrays.sort(pt);
  	Point mn = pt[0];
  	Point mx = pt[3];
    //System.out.println(a+" -> "+b+" -> "+c+" -> "+d);
    //System.out.println("line: "+ (new LineSegment(mn,mx)).toString());
  	return new LineSegment(mn, mx);
  }

  private LineSegment[] updateList() {
  	List<LineSegment> lines = new ArrayList<LineSegment>();
  	for (int i = 0; i < points.length; i++)
  	  for (int j = i+1; j < points.length; j++) 
  	  	for (int k = j+1; k < points.length; k++) 
  	  	  for (int l = k+1; l < points.length; l++) 
  	  	  	if (inLine(points[i], points[j], points[k], points[l]))
  	  	  	  lines.add(line(points[i], points[j], points[k], points[l]));

  	return lines.toArray(new LineSegment[lines.size()]);
  }               // the line segments

  /*public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    int i = 0;
    //StdDraw.setPenRadius(0.05);
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.setXscale(1000,100000);
    StdDraw.setYscale(1000,100000);
    //System.out.println(n);
    while (i < n) {
      int p1 = Integer.parseInt(in.readString());
      int p2 = Integer.parseInt(in.readString());
      //System.out.println(p1+" "+p2);
      points[i] = new Point(p1,p2);
      i++;
    }
    BruteCollinearPoints brute = new BruteCollinearPoints(points);
    //(2000,22000) -> (13000,21000) -> (23000,16000) -> (28000,13500)
    //System.out.println(brute.inLine(new Point(2000,22000),new Point(13000,21000), 
    //                                new Point(23000,16000), new Point(28000,13500)));
    System.out.println(brute.segments());
    points[2] = new Point(2,3);
    for(Point point: brute.points) 
      System.out.println(point);
    System.out.println(brute.numberOfSegments());
    System.out.println(brute.numberOfSegments());
    for(LineSegment segment: brute.segments()) {
      System.out.println(segment);
      segment.draw();
    }
  }*/
}