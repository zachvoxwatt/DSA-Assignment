package geom;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;

import dsa2020_GUI.*;

public class Graph extends GeomObject 
{
	public enum GraphMode { LINE, SCATTER }

	protected Viewport vp = null;
	protected Point2D[] ps = null;
	
	private GraphMode mode = null;
	
	public Graph(Point2D[] p, double xm, double xmx, double ym, double ymx)
	{
		this.vp = new Viewport(xm, xmx, ym, ymx);
		this.ps = p;
	}
	
	public Graph(Point2D[] p, double xm, double xmx, double ym, double ymx, Color c)
	{
		this.vp = new Viewport(xm, xmx, ym, ymx);
		this.ps = p;
		this.edge_c = c;
	}
	
	public Graph(Point2D[] p)
	{
		copyPoints(p);
	}
	
	public Graph(Point2D[] p, Color c)
	{
		copyPoints(p);
		this.edge_c = c;
		this.face_c = c;
	}

	public GraphMode getGraphMode() { return this.mode; }
	public void setGraphMode(GraphMode m) { this.mode = m; }
	
	public Viewport getViewport() { return this.vp; }
	
	private void copyPoints(Point2D[] pss)
	{
		this.ps = pss;
		this.vp = new Viewport(pss[0].getX(), pss[0].getX(), pss[0].getY(), pss[0].getY());
		
		for (int idx = 0; idx < pss.length; idx++)
		{
			this.vp.addPoint(pss[idx]);
		}
	}
	
	public static Graph quadratic(double a, double b, double c, double xm, double xmx, double step)
	{
		int n = (int) ((xmx - xm) / step + 1);
		Point2D[] list = new Point2D[n];
		
		double px = xm;
		
		for (int idx = 0; idx < list.length; idx++)
		{
			double py = a*Math.pow(px, 2) + b * px + c - 0.06;
			
			list[idx] = new Point2D(px, py);
			
			px += step;
		}
		return new Graph(list, Color.blue);
	}
	//in this method, i made a small adjustment to minimize the frequency of the sin() wave.
	
	public static Graph sin(double a, double xm, double xmx, double step)
	{
		int n = (int) ((xmx - xm) / step + 1);
		Point2D[] list = new Point2D[n];
		
		double px = xm + 0.225;
		
		for (int idx = 0; idx < list.length; idx++)
		{
			double py = a * Math.sin(20*px) + 0.25;
			
			list[idx] = new Point2D(px, py);
			px += step;
		}
		
		return new Graph(list, Color.red);
	}
	
	@Override
	public void draw(Graphics g, SpaceMapping map)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		if (this.mode == GraphMode.LINE)
		{
			if (this.ps == null) return;
			
			int[] x = new int[this.ps.length];
			int[] y = new int[this.ps.length];
			
			for (int idx = 0; idx < ps.length; idx++)
			{
				Point2D devPoint = map.logic2Device(this.ps[idx]);
				x[idx] = (int) devPoint.getX();
				y[idx] = (int) devPoint.getY();
			}
			
			g2.setColor(this.edge_c);
			Stroke style = new BasicStroke(this.line_weight);
			
			g2.setStroke(style);
			g2.drawPolyline(x, y, y.length);
		}
		
		else if (this.mode == GraphMode.SCATTER)
		{
			for (int idx = 0; idx < ps.length; idx++)
			{
				ps[idx].setColor(this.face_c);
				this.ps[idx].draw(g, map);
			}
		}
		
		else 
		{
			throw new UnsupportedOperationException("Feature unsupported.");			
		}
	}
}
