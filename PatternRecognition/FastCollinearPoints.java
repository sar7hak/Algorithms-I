import java.util.*;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
	private Point[] points;
	private LineSegment[] set;

	public FastCollinearPoints(Point[] points){
		this.points = points;
		Arrays.sort(points);
     		set = updateSet();
	}

	public int numberOfSegments(){
		return set.length;
	}
	
	public LineSegment[] segments(){
		return set;	
	}

	private LineSegment[] updateSet() {
		List<LineSegment> set = new ArrayList<LineSegment>();
   		List<Point> pt = new ArrayList<Point>();
		Point prevPoint, currPoint;
		for(Point point: points) {
			Point[] localPoints = points.clone();
			Arrays.sort(localPoints, point.slopeOrder());
			for (int i = 1; i < points.length - 1; i++) {
				prevPoint = localPoints[i];
       				currPoint = localPoints[i+1];
       				if (point.slopeTo(prevPoint) == point.slopeTo(currPoint)) {
					if (pt.isEmpty())
          					pt.add(prevPoint);
          				pt.add(currPoint);
				}
				if (point.slopeTo(prevPoint) != point.slopeTo(currPoint) || i == points.length - 2) {
					if (pt.size() > 2) {
						if (pt.get(0).compareTo(point) > 0) {
							set.add(new LineSegment(point,pt.get(pt.size() - 1)));
						}
					}
					pt.clear();
				}
				prevPoint = currPoint;
			}
			pt.clear();	
		}
		return set.toArray(new LineSegment[set.size()]);
	}

/*	public static void main(String[] args) {
		In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    int i = 0;
	    StdDraw.setPenColor(StdDraw.BLUE);
   	    StdDraw.setXscale(900,100000);
    	    StdDraw.setYscale(900,100000);
	    while (i < n) {
	      int p1 = Integer.parseInt(in.readString());
	      int p2 = Integer.parseInt(in.readString());
	      //System.out.println(p1+" "+p2);
	      points[i] = new Point(p1,p2);
	      i++;
	    }
	    FastCollinearPoints fast = new FastCollinearPoints(points);
	    System.out.println(fast.numberOfSegments());
	    for (LineSegment segment: fast.segments())
               	segment.draw();
	}*/
}
