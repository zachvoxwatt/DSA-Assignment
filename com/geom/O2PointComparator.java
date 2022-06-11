package geom;

import java.util.Comparator;

public class O2PointComparator implements Comparator<Point2D>
{
	@Override
	public int compare(Point2D p1, Point2D p2)
	{
		double d1 = Math.sqrt( Math.pow(p1.getX() - 0, 2) + Math.pow(p1.getY() - 0, 2));
		double d2 = Math.sqrt( Math.pow(p2.getX() - 0, 2) + Math.pow(p2.getY() - 0, 2));
		
		if (d1 > d2) return 1;
		
		else if (d1 < d2) return -1;
		
		else return 0;
	}
}
