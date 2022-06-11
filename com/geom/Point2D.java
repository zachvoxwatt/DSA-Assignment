package geom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

@SuppressWarnings(value = "unused")
public class Point2D extends GeomObject
{
	private static int POINT_HALF_SIZE = 2;
	private double x, y;
	
	//[Constructors]
	public Point2D(double x, double y)
	{
		this.x = x; 
		this.y = y;
	}
	
	public Point2D(Point2D p)
	{
		this.x = p.x;
		this.y = p.y;	
	}
	
	public Point2D()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Point2D clone()
	{
		return this;
	}
	
	//[Getters / Setters]
	public void setX(double i) { this.x = i; }
	public void setY(double i) { this.y = i; }
	
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	
	//[Conversion to String]
	@Override
	public String toString()
	{
		System.out.printf("(%.2f, %.2f)",this.x, this.y);
		return null;
	}
	
	//[Services]
	public static Point2D[] generate(int n, double min, double max) 
	{
		double px, py;
		
		Point2D[] arrlist = new Point2D[n];
		Random rand = new Random();
		
		for (int j = 0; j < arrlist.length; j++)
		{
			px = rand.nextDouble() * (max - min) + min;
			py = rand.nextDouble() * (max - min) + min;

			arrlist[j] = new Point2D(px, py);
		}
		
		return arrlist;
	}
	
	public static Point2D[] linear(int n, double a, double b, double xm, double xmx)
	{
		Point2D[] list = new Point2D[n];
		double step = (xmx - xm) / (n-1);
		double lx = xm;
		double ly = 0;
		
		for (int idx = 0; idx < n; idx++)
		{
			ly = a*lx + b;
			list[idx].setX(lx);
			list[idx	].setY(ly);
			
			lx += step;
		}
		
		return list;
	}
	
	public static double distanceAB(Point2D a, Point2D b)
	{
		double d = Math.sqrt( Math.pow( (b.getX() - a.getX()), 2 ) + Math.pow( (b.getY() - a.getY()), 2 ) );
		
		return d;
	}
	
	public double distanceTo(Point2D p)
	{
		double d = Math.sqrt( Math.pow( (p.getX() - this.getX()), 2 ) + Math.pow( (p.getY() - this.getY()), 2 ) );
		
		return d;
	}
	
	public void setColor(Color c)
	{
		this.face_c = c;
	}
	
	@Override
	public void draw(Graphics g, SpaceMapping map)
	{
		Graphics2D g2 = (Graphics2D) g;
		Point2D p = map.logic2Device(this.getX(), this.getY());
		
		int x = (int) p.getX() - POINT_HALF_SIZE;
		int y = (int) p.getY() - POINT_HALF_SIZE;
		
		g2.setColor(this.face_c);
		g2.fillRect(x, y, 5, 5);
	//	g2.fillOval(x, y, POINT_HALF_SIZE, POINT_HALF_SIZE);
	}
}