package geom;

import java.util.Comparator;

public class PointComparator implements Comparator<Point2D>
{
	@Override
	public int compare(Point2D p1, Point2D p2) 
	{
		if (Math.abs(p1.getX() - p2.getX()) < 1e-7) return 0;
		
		else if (p1.getX() < p2.getX()) return -1;
		
		else return 1;
	}
}