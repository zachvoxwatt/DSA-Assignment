package sorting;

import geom.Point2D;
import geom.PointComparator;
import sorting._SortType.*;

@SuppressWarnings(value = {"unchecked","rawtypes"})
public class SortingEvaluation
{	
	public static Point2D[] timeIt(ISort algo, int n_elem, int n_exec, boolean sortAsc)
	{
		Point2D[] algoExecTime = new Point2D[n_elem];
		algoExecTime[0] = new Point2D();
		
		for (int idx = 1; idx < n_elem; idx++)
		{
			double duration = 0;
			
			//Execute n times to get a more precise and accurate result.
			for (int idx_exe = 0; idx_exe <= n_exec; idx_exe++)
			{
				Point2D[] list = Point2D.generate(idx, -25, 25);
				
				double timer_start = System.nanoTime();
					algo.sort(list, new PointComparator(), sortAsc);
				double timer_stop = System.nanoTime();
				
				duration += (timer_stop - timer_start) / (n_exec * 1000000);
			}
			
			algoExecTime[idx] = new Point2D(idx, duration);
		}
		
		return algoExecTime;
	}
	
	public static void displayResult(Point2D[] ps, SORT_TYPE sort)
	{
		switch (sort)
		{
			case INSERTION:
				System.out.printf("Straight Insertion Sort %n- Performance evaluation%n%n");
				System.out.printf("Size	|	Duration (in ms)%n");
				break;
				
			case BUBBLE:
				System.out.printf("Bubble Sort %n- Performance evaluation%n%n");
				System.out.printf("Size	|	Duration (in ms)%n");
				break;
				
			case SELECTION:
				System.out.printf("Straight Selection Sort %n- Performance evaluation%n%n");
				System.out.printf("Size	|	Duration (in ms)%n");
				break;
			
			case SHELL:
				System.out.printf("Shell Sort %n- Performance evaluation%n%n");
				System.out.printf("Size	|	Duration (in ms)%n");
				break;
		}
		
		for (int i = 0; i < ps.length; i++)
		{
			String info = String.format("%d	|	%.6f", i, ps[i].getY());
			System.out.println(info);
		}
	}
}