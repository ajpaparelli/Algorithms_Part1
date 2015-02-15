import java.util.Arrays;


public class Brute {

	/**
	 * @param args
	 */
	public static boolean colinear(Point p1, Point p2, Point p3, Point p4) {
		double s1 = p1.slopeTo(p2);
		double s2 = p2.slopeTo(p3);
		double s3 = p3.slopeTo(p4);
		if(s1 == s2 && s2==s3 )
			return true;
		else
			return false;
	}
	
	public static void draw(Point p1, Point p2, Point p3, Point p4) {
		Point[] p = {p1, p2, p3, p4};
		Arrays.sort(p);
		StdOut.print(p[0] + "->" + p[1] + "->" + p[2] + 
				"->" + p[3]);
		StdOut.print();
		for(int i = 0; i<4; i++)
			p[i].draw();
		p[0].drawTo(p[3]);
	}
	
	public static void main(String[] args) {
if (args.length < 1)
return;
String filename = args[0];
In input = new In(filename);
int N = input.readInt();
if (N < 4)
return;
Point[] points = new Point[N];
for (int i = 0; i < N; ++i) {
int x = input.readInt();
int y = input.readInt();
points[i] = new Point(x, y);
}
// generate 4 combination of n
int t = 4;
int[] cArray = new int[t + 3];
for (int j = 1; j < cArray.length; ++j) {
cArray[j] = j - 1;
}
cArray[t + 1] = N;
cArray[t + 2] = 0;
// rescale coordinates and turn on animation mode
StdDraw.setXscale(0, 32768);
StdDraw.setYscale(0, 32768);
StdDraw.setCanvasSize(800, 800);
StdDraw.setPenRadius(.005);
StdDraw.setPenColor(StdDraw.BLUE);
StdDraw.show(0);
while (true) {
Point p1 = points[cArray[1]];
Point p2 = points[cArray[2]];
Point p3 = points[cArray[3]];
Point p4 = points[cArray[4]];
if (colinear(p1, p2, p3, p4)) {
draw(p1, p2, p3, p4);
}
int j = 1;
while (cArray[j] + 1 == cArray[j + 1]) {
cArray[j] = j - 1;
j = j + 1;
}
if (j > t)
break;
cArray[j] = cArray[j] + 1;
}
}

}
