import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private   int N,num=0;
	private      LineSegment lineSeg[];
 private final ArrayList<LineSegment> res = new ArrayList<>();

	private  Point[] mPoints;
private  Point[] nPoints;
	

   // finds all line segments containing 4 or more points
   public FastCollinearPoints(Point[] points) {
	   checkArguments(points);
	   checkDuplicateArguments(points);
	   N = points.length;
	   mPoints = new Point[N];
nPoints = new Point[N];
	   for(int i = 0; i < N; i++){
		   mPoints[i] = points[i];
nPoints[i] = points[i];
	   }
   }
   
   // the number of line segments
   public int numberOfSegments() {
          
	   return res.size();
   }

   // the line segments
   public   LineSegment[] segments() {
	  int count=1;
Point max=null,min=null;
	   for(int i = 0; i < N; i++){
for (int p=0;p<N&&i!=0;p++)
mPoints[p] = nPoints[p];
		   Arrays.sort(mPoints, mPoints[i].slopeOrder());

		   
		   for(int j = 1; j < N; j++){
			double curSlope = mPoints[0].slopeTo(mPoints[j]);
if(count==1)
{
max=mPoints[0];
min=mPoints[0];
}
	
			   if( (j+1<N) && (curSlope==mPoints[0].slopeTo(mPoints[j+1]))) {
if(max.compareTo(mPoints[j])> 0) {max = mPoints[j];}
if(min.compareTo(mPoints[j]) < 0) {min = mPoints[j];}
count++;
continue;
		   
			  }
if(count>=3)
{
if(max.compareTo(mPoints[j]) >0) {max = mPoints[j];}
if(min.compareTo(mPoints[j])< 0) {min = mPoints[j];}
}
if(count>=3&&nPoints[i]==min)
{
res.add(new LineSegment(max,min));
num++;
}
count=1;

		    }
		   }
	   int index = 0;
	  //lineSeg = res.toArray(new LineSegment[res.size()]);
  return res.toArray(new LineSegment[res.size()]);
   }
   
 
  
	
	private void checkArguments(Point[] points) {
		if(points == null) throw new java.lang.IllegalArgumentException();
		for(int i = 0; i < points.length; i++){
			if(points[i] == null) throw new java.lang.IllegalArgumentException();
		}
	}
	
	private void checkDuplicateArguments(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
	}
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    System.out.println("numOfSeg = "+collinear.numberOfSegments());
	    StdDraw.show();
		
	}
}