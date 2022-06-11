package geom;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GeomObject
{
	protected Color edge_c;
	protected Color face_c;
	protected int line_weight = 1;
	
	public GeomObject()
	{
		edge_c = new Color(20, 200, 20);
		face_c = Color.RED;
	}
	
	public abstract void draw(Graphics g, SpaceMapping map);
}
