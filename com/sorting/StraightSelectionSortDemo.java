package sorting;

import geom.Point2D;
import geom.PointComparator;

public class StraightSelectionSortDemo 
{
	public static void demo(boolean sortAsc)
	{
		int no = 30;
		Point2D[] plist = Point2D.generate(no, -10, 20);
		
		System.out.println("DEMO FOR SELECTION SORT:");
		System.out.println("---------------------------------------");
		System.out.println("Unsorted list:");
		System.out.println("---------------------------------------");
		System.out.println();
		
		for (int idx = 0; idx < plist.length; idx++)
		{
			System.out.printf("P.%d	|	%.2f, %.2f%n", idx + 1, plist[idx].getX(), plist[idx].getY());
		}
		
		
		StraightSelectionSort<Point2D> algo = new StraightSelectionSort<>();
		PointComparator pc = new PointComparator();
			algo.sort(plist, pc, sortAsc);
			
		
		System.out.println();
		
		if (sortAsc == true) System.out.println("Sorted (by X-coordinates, ascending):");
		
		else System.out.println("Sorted (by X-coordinates, descending):");
		
		System.out.println("---------------------------------------");
		
		for (int idx = 0; idx < plist.length; idx++)
		{
			System.out.printf("P.%d	|	%.2f, %.2f%n", idx + 1, plist[idx].getX(), plist[idx].getY());
		}
	}
}