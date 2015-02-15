import java.util.Arrays;


public class Fast {
	
	public static void drawLine(Point[] pl) {
		String s = new String();
		int len = pl.length;
		for(int i = 0; i < len; i++) {
			s += pl[i].toString();
			if(i != len-1)
				s+= "->";
			pl[i].draw();
		}
		StdOut.println(s);
		pl[0].drawTo(pl[len-1]);
	}
	
	public static void outputseg(Point op, Point[] pl, int idx, int adj) {
		Point[] sp = new Point[adj+1];
		for(int i = 0; i < adj; i++)
			sp[i] = pl[idx+i];
		
		sp[adj] = op;
		Arrays.sort(sp);
		if(op.compareTo(sp[0]) == 0)
			drawLine(sp);
	}
	
	public static void defineLine(Point op, Point[] pl) {
		double s1 = op.slopeTo(pl[1]);
		int adj = 1;
		int idx = 1;
		for(int i = 2; i < pl.length; i++) {
			double s2 = op.slopeTo(pl[i]);
			if(s2 == s1)
				adj++;
			else if(op.compareTo(pl[i]) == 0)
				adj++;
			else if(adj >= 3) {
				outputseg(op, pl, idx, adj);
				adj = 1;
				idx = i;
				s1 = s2;
			}
			else {
				idx = i;
				s1 = s2;
				adj = 1;		
			}
		}
		if(adj >= 3) {
			outputseg(op, pl, idx, adj);
		}
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
		Point[] spoints = new Point[N];
		for (int i = 0; i < N; ++i) {
			int x = input.readInt();
			int y = input.readInt();
			points[i] = new Point(x, y);
			spoints[i] = points[i];
		}
		
		for(int i = 0; i < N; i++) {
			Point p = points[i];
			Arrays.sort(spoints, p.SLOPE_ORDER);
			defineLine(p, spoints);
		}
	}

}
