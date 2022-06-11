package dsa2020_GUI;

import java.awt.Color;
import java.awt.Graphics;

public class Legend 
{
	static int w = 100;
	static int h = 250;
	
	public static void render(Graphics g, int a, int b)
	{
		g.setColor(Color.BLACK);
		
		g.drawRect(a + 75, b + 25, w + 15, h - 140);
		g.drawString("Legend", a + 115, b + 40);
		g.drawLine(a + 115, b + 43, a + 155, b + 43);
		
		g.drawString("Bubble Sort", a + 105, b + 65);
		g.drawString("Insertion Sort", a + 105, b + 85);
		g.drawString("Selection Sort", a + 105, b + 105);
		g.drawString("Shell Sort", a + 105, b + 125);
		
		g.setColor(Color.cyan);
		g.fillOval(a + 85, b + 55, 10, 10);
		
		g.setColor(Color.blue);
		g.fillOval(a + 85, b + 75, 10, 10);
		
		g.setColor(Color.red);
		g.fillOval(a + 85, b + 95, 10, 10);
		
		g.setColor(Color.green);
		g.fillOval(a + 85, b + 115, 10, 10);
	}
}
