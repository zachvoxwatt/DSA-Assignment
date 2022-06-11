package sorting;

import java.util.Comparator;

import geom.Point2D;

public class StraightInsertionSort<E> implements ISort<E>
{	
	//Method name: Insertion sort()
	//Objective: Sorts elements in a given array by moving the elements until the array is sorted.
	
	//Space Complexity: O(1)
		//Reason: This method only declares some variables
	
	//Time Complexity: O(n^2)
		//Reason: The first while loop will have to execute N times as the worse case
		//The second loop also will have to perform the worse case as N times to complete.
		//Therefore, O(n * n) = O(n^2)
	
	@Override
	public void sort(E[] arr, Comparator<E> comp, boolean sortAsc)
	{
		int curr, itor;
		E tmp;
		
		curr = 1;
		
		while (curr < arr.length)
		{
			tmp = arr[curr];
			itor = curr - 1;
			
			if (sortAsc == true)
			{
				while ( itor >= 0 && comp.compare(tmp, arr[itor]) < 0 )
				{
					arr[itor + 1] = arr[itor];
					itor -= 1;
				}
			}
			
			else
			{
				while ( itor >= 0 && comp.compare(tmp, arr[itor]) > 0 )
				{
					arr[itor + 1] = arr[itor];
					itor -= 1;
				}
			}
			arr[itor + 1] = tmp;
			curr += 1;
		}
	}
	
	@Override
	public Point2D getCOM(Point2D[] list)
	{
		double sum_x = 0, sum_y = 0;
		
		for (int i = 0; i < list.length; i++)
		{
			sum_x += list[i].getX();
			sum_y += list[i].getY();
		}
		
		sum_x /= list.length;
		sum_y /= list.length;
		
		return new Point2D(sum_x, sum_y);
	}
}