package dsa2020_GUI;

import geom.Point2D;

public class Viewport 
{
	private double xMin, yMin, xMax, yMax;
	
	public Viewport(double a, double b, double c, double d)
	{
		this.xMin = a;
		this.xMax = b;
		this.yMin = c;
		this.yMax = d;
	}
	
	public Viewport clone() { return this; }
	
	public void combine(Viewport vp)
	{
		this.xMax = Math.max(this.xMax, vp.xMax);
		this.xMin = Math.min(this.xMin, vp.xMin);
		this.yMax = Math.max(this.yMax, vp.yMax);
		this.yMin = Math.min(this.yMin, vp.yMin);
	}
	
	public void addPoint(Point2D p)
	{
		this.xMax = Math.max(this.xMax, p.getX());
		this.xMin = Math.min(this.xMin, p.getX());
		this.yMax = Math.max(this.yMax, p.getY());
		this.yMin = Math.min(this.yMin, p.getY());
	}
	
	public void setxMax(double i) { this.xMax = i; }
	public void setyMax(double i) { this.yMax = i; }
	public void setxMin(double i) { this.xMin = i; }
	public void setyMin(double i) { this.yMin = i; }
	
	public double getxMax() { return this.xMax; }
	public double getxMin() { return this.xMin; }
	public double getyMax() { return this.yMax; }
	public double getyMin() { return this.yMin; }
}
