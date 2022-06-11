package geom;

import dsa2020_GUI.Viewport;

public class SpaceMapping 
{
    private int minDevX, maxDevX;
    private int minDevY, maxDevY;
    private double minLogX, maxLogX;
    private double minLogY, maxLogY;
    
    
    public SpaceMapping()
    {
        this.minDevX = 0;
        this.maxDevX = 800;
        this.minDevY = 0;
        this.maxDevY = 600;
        
        this.minLogX = 0;
        this.maxLogX = 500.0;
        this.minLogY = 0;
        this.maxLogY = 1.0;
    }
    
    public double wL()
    {
        return this.maxLogX - this.minLogX;
    }
    
    public double hL()
    {
        return this.maxLogY - this.minLogY;
    }
    
    public double wD()
    {
        return this.maxDevX - this.minDevX;
    }
    
    public double hD()
    {
        return this.maxDevY - this.minDevY;
    }
    
    public double sxL2D() { return wD()/wL(); }
    public double syL2D() { return hD()/hL(); }
    public double sxD2L() { return wL()/wD(); }
    public double syD2L() { return hL()/hD(); }
    
    public Point2D oLinD()
    {
    	double a = this.minDevX - this.minLogX*this.sxL2D();
    	double b = this.maxDevY + this.minLogY*this.syL2D();
        return new Point2D(a, b);
    }
    
    public double step()
    {
        return 5.0*Math.min(this.sxD2L(), this.syD2L());
    }
    
    public double stepx10()
    {
        return 10.0*this.sxD2L();
    }
    
    public double stepy10()
    {
        return 10.0*this.syD2L();
    }
    
    public void updateDevViewPort(int A, int B, int C, int D)
    {
        this.minDevX = A;
        this.maxDevX = B;
        this.minDevY = C;
        this.maxDevY = D;
    }
    
    public void updateLogViewPort(double a, double b, double c, double d)
    {
        this.minLogX = a;
        this.maxLogX = b;
        this.minLogY = c;
        this.maxLogY = d;
    }
    
    public void updateLogViewPort(Viewport vp)
    {
        this.minLogX = vp.getxMin();
        this.maxLogX = vp.getxMax();
        this.minLogY = vp.getyMin();
        this.maxLogY = vp.getyMax();
    }
    
    public Point2D device2Logic(Point2D p)
    {
        return device2Logic((int) p.getX(), (int) p.getY());
    }
    
    public Point2D device2Logic(int devX, int devY)
    {
        double txD2L = this.oLinD().getX() * sxD2L();
        double tyD2L = - this.oLinD().getY() * syD2L();
        double x =  sxD2L() * devX - txD2L;
        double y = -syD2L() * devY - tyD2L;
        
        return new Point2D(x, y);
    }
    
    public Point2D logic2Device(Point2D p)
    {
        return logic2Device(p.getX(), p.getY());
    }
    
    public Point2D logic2Device(double a, double b)
    {
        double txL2D = this.oLinD().getX();
        double tyL2D = -this.oLinD().getY();
        double x = sxL2D() * a + txL2D;
        double y = -syL2D() * b - tyL2D;
        
        return new Point2D(x, y);
    }
    
    public Viewport getLogViewport()
    {
        return new Viewport(this.minLogX, this.maxLogX, this.minLogY, this.maxLogY);
    }
    
    public Viewport getDevViewport()
    {
        return new Viewport(this.minDevX, this.maxDevX, this.minDevY, this.maxDevY);
    }
}