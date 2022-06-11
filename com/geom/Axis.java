package geom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import dsa2020_GUI.Viewport;

public class Axis extends GeomObject
{
	public static final int graph_no = 10;
	private Viewport vp;
	
	static int legend_x, legend_y;
	
	Graph[] grs = new Graph[graph_no];
	int ngr = 0;
	
	public Axis(double a, double b, double c, double d)
	{
		this.vp = new Viewport(a, b, c, d);
	}
	
	public Axis(Viewport v)
	{
		this.vp = v.clone();
	}
	
	public void graphAdder(Graph g)
	{
		if (this.ngr >= graph_no) return;
		
		this.grs[this.ngr++] = g;
		this.getViewport().combine(g.getViewport());
	}
	
	public void addGraph(Graph g)
	{
		this.graphAdder(g);
	}
	
	public void addGraph(Graph g, Color c)
	{
		this.graphAdder(g);
		g.edge_c = c;
	}
	
	public void addGraph(Graph g, Color c, int line_weight)
	{
		this.graphAdder(g);
		g.line_weight = line_weight;
		g.edge_c = c;
	}
	
	public Viewport getViewport()
	{
		return vp;
	}
	@Override
	public void draw(Graphics g, SpaceMapping map) 
	{
		drawXAxis(g, map);
		drawYAxis(g, map);
		
		for (int idx = 0; idx < this.ngr; idx++)
		{
			this.grs[idx].draw(g, map);
		}
	}
	
	public void drawXAxis(Graphics g, SpaceMapping sp)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		Point2D devXL = sp.logic2Device(new Point2D(this.getViewport().getxMin(), this.getViewport().getyMin()));
		Point2D devXR = sp.logic2Device(new Point2D(this.getViewport().getxMax(), this.getViewport().getyMin()));
		
		int x1 = (int) devXL.getX(); int y1 = (int) devXL.getY();
		int x2 = (int) devXR.getX(); int y2 = (int) devXR.getY();
		
		g.setColor(Color.black);
		
		Stroke style = new BasicStroke(2);
		g2.setStroke(style);
		g2.drawLine(x1, y1, x2, y2);
		g2.drawString("N elements", x2 + 5, y2 + 3);
		
		double step = 5 * sp.stepx10();
		
		int hyphens = (int) ((this.getViewport().getxMax() - this.getViewport().getxMin()) / step ) + 1;
		double x_incre = this.getViewport().getxMin();
		
		String info = null;
		
		for (int idx = 0; idx < hyphens; idx++)
		{
			info = String.format("%.2f", x_incre);
			
			Point2D devHyphenPOS = sp.logic2Device(new Point2D(x_incre, this.getViewport().getyMin()));
			
			x1 = (int) devHyphenPOS.getX(); y1 = (int) devHyphenPOS.getY();
			
			FontMetrics fm = g.getFontMetrics();
			
			int sx = x1 - fm.stringWidth(info) / 2;
			int sy = y1 + fm.getHeight();
			
			g.setColor(Color.black);
			g.drawString(info, sx, sy);
			
			x2 = x1; y2 = y1 + 5;
			
			g.setColor(Color.blue);
			style = new BasicStroke(1);
			g2.setStroke(style);
			g2.drawLine(x1, y1, x2, y2);
			
			x_incre += step;
		}
	}
	
	public void drawYAxis(Graphics g, SpaceMapping sp)
	{
        Graphics2D g2 = (Graphics2D) g;
        
        Point2D devYT = sp.logic2Device(new Point2D(this.getViewport().getxMin(), this.getViewport().getyMax()));
        Point2D devYB = sp.logic2Device(new Point2D(this.getViewport().getxMin(), this.getViewport().getyMin()));
        
        int x1 = (int)devYT.getX(), y1 = (int)devYT.getY();
        int x2 = (int)devYB.getX(), y2 = (int)devYB.getY();
        
        g.setColor(Color.black);
        Stroke style = new BasicStroke(2);
        g2.setStroke(style);
        g2.drawLine(x1, y1, x2, y2);
        g2.drawString("Time (ms)", x1 - 25, y1 - 5);
        
        legend_x = x1; legend_y = y1;
        
        double step = 5 * sp.stepy10();
        int hyphens = (int)((this.getViewport().getyMax() - this.getViewport().getyMin())/step) + 1;
        double y_incre = this.getViewport().getyMin();
        String info = "";

        for(int idx=0; idx < hyphens; idx++)
        {
            info = String.format("%5.2f", y_incre);
            
            Point2D devHyphenPOS = sp.logic2Device(new Point2D(this.getViewport().getxMin(), y_incre));
            int x = (int)devHyphenPOS.getX(), y = (int)devHyphenPOS.getY();    
            FontMetrics fm = g.getFontMetrics();
            
            int sx = x - fm.stringWidth(info) - 10;
            int sy = y + fm.getAscent()/2;
            
            g.setColor(Color.black);
            g.drawString(info, sx, sy);
            x2 = x - 10; y2 = y;
            
            g.setColor(Color.blue);
            style = new BasicStroke(1);
            g2.setStroke(style);
            g2.drawLine(x, y, x2, y2); 
            
            y_incre += step;
        }
    }
	
	public static int getLX() { return legend_x; }
	public static int getLY() { return legend_y; }
}
