package dsa2020_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import geom.Axis;
import geom.Graph;
import geom.Point2D;
import geom.SpaceMapping;
import geom.Graph.GraphMode;
import sorting.*;

@SuppressWarnings(value= {"unused"})
public class WorkingPanel extends JPanel implements MouseMotionListener, MouseListener, ItemListener, 
															ActionListener, ComponentListener
{
	private final static long serialVersionUID = 2;
    private boolean graphPressed = false;
    
    private SpaceMapping sp = new SpaceMapping();
    private Axis a = new Axis(sp.getLogViewport());
    
    Graph bub_g; Graph ins_g; Graph sel_g; Graph shell_g;
    BubbleSort<Point2D> bub_algo; ShellSort<Point2D> shell_algo;
    StraightInsertionSort<Point2D> ins_algo; StraightSelectionSort<Point2D> sel_algo;
    
    Point2D[] bub_data; Point2D[] sel_data; Point2D[] shell_data; Point2D[] ins_data;
    
    public WorkingPanel()
    {
        this.setBorder(BorderFactory.createEtchedBorder()); 
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);
        
        initializeGraphs();
        
        repaint();
    }
   
    public void initializeGraphs()
    {
    	bub_algo = new BubbleSort<>();
        shell_algo = new ShellSort<>();
        ins_algo = new StraightInsertionSort<>();
        sel_algo = new StraightSelectionSort<>();
    }
    
    public void processGraphs()
    {
    	bub_data = SortingEvaluation.timeIt(bub_algo, 500, 100, true);
        ins_data = SortingEvaluation.timeIt(ins_algo, 500, 100, true);
        sel_data = SortingEvaluation.timeIt(sel_algo, 500, 100, true);
        shell_data = SortingEvaluation.timeIt(shell_algo, 500, 100, true);
        
    	bub_g = new Graph(bub_data, Color.cyan);
    	ins_g = new Graph(ins_data, Color.blue);
    	sel_g = new Graph(sel_data, Color.red);
    	shell_g = new Graph(shell_data, Color.green);
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
    	Point2D lp = this.sp.device2Logic(e.getX(), e.getY());
    	
        String message = String.format("mouseDragged: Device(x,y)=(%d,%d) | Logic(x,y) = (%.2f, %.2f)", e.getX(), e.getY(), lp.getX(), lp.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
    	Point2D lp = this.sp.device2Logic(e.getX(), e.getY());
    	
        String message = String.format("mouseMoved: Device(x,y)=(%d,%d) | Logic(x,y) = (%.2f, %.2f)", e.getX(), e.getY(), lp.getX(), lp.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if((e.getClickCount() == 2) && !e.isConsumed())
        {
            String message = String.format("Mouse Double Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            MainFrame.infoPanel.println(message);
            e.consume();
        }
        else
        {
            String message = String.format("Mouse Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            MainFrame.infoPanel.println(message);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        String message = String.format("mousePressed: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        String message = String.format("mouseReleased: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        String message = String.format("mouseEntered: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        String message = String.format("mouseExited: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        int state = e.getStateChange();
        if(state == ItemEvent.SELECTED){
            MainFrame.infoPanel.println("Selected");
            MainFrame.btnSelect.setText("Selecting");
        }
        else{
            MainFrame.infoPanel.println("DeSelected");
            MainFrame.btnSelect.setText("Drawing");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == MainFrame.btnCircle)
        {
            MainFrame.infoPanel.println("action: draw Circle");
        }
        
        else if(e.getSource() == MainFrame.btnRect)
        {
            MainFrame.infoPanel.println("action: draw Rect");
        }
        
        else if(e.getSource() == MainFrame.btnGraph)
        {
        	MainFrame.infoPanel.println("Initializing Graph. Please wait...");
        	try
        	{
        		TimeUnit.SECONDS.sleep(1);
        	}
        	
        	catch (InterruptedException ei) {}
        	
        	processGraphs();
        	graphPressed = true;
        	updateScreen();
        }
    }

	@Override
	public void componentResized(ComponentEvent e) 
	{
		Dimension d = this.getSize();
		int xGap = 50, yGap = 20;
		this.sp.updateDevViewPort(xGap, d.width - 2*xGap, yGap, d.height - 2*yGap);
		updateScreen();
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	
	public void updateScreen() { repaint(); }
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (graphPressed == true)
		{
			super.paintComponent(g);
			
			bub_g.setGraphMode(GraphMode.SCATTER);
			bub_g.draw(g, sp);
			
			ins_g.setGraphMode(GraphMode.SCATTER);
			ins_g.draw(g, sp);
			
			sel_g.setGraphMode(GraphMode.SCATTER);
			sel_g.draw(g, sp);
			
			shell_g.setGraphMode(GraphMode.SCATTER);
			shell_g.draw(g, sp);

			MainFrame.infoPanel.println("");
			MainFrame.infoPanel.println("");
			MainFrame.infoPanel.println("Successfully rendered Graphs");
			
		}
		
		Legend.render(g, Axis.getLX(), Axis.getLY());
		a.draw(g, sp);
	}
}
