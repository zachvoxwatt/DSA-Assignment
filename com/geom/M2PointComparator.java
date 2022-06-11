package geom;

import java.util.Comparator;

public class M2PointComparator implements Comparator<Point2D>
{
	private double x, y;
	
	public M2PointComparator(Point2D p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public double getCOM_X() {	return this.x;	}
	public double getCOM_Y() {	return this.y;	}
	
	@Override
	public int compare(Point2D p1, Point2D p2)
	{
		double d1 = Math.sqrt( Math.pow(p1.getX() - this.x, 2) + Math.pow(p1.getY() - this.y, 2));
		double d2 = Math.sqrt( Math.pow(p2.getX() - this.x, 2) + Math.pow(p2.getY() - this.y, 2));
		
		if (d1 > d2) return 1;
		
		else if (d1 < d2) return -1;
		
		else return 0;
	}
}
