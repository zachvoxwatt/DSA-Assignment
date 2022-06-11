package sorting;

import java.util.Comparator;
import geom.Point2D;

//Shell Sort is the best sort method of all 4 methods
//In spite of its Time complexity being O(N^2) in our case, it allows programmers to move elements of indexes
//that are not near each other, whilst Bubble Sort can only sort near elements.

public class ShellSort<E> implements ISort<E>
{
	@Override
	public void sort(E[] arr, Comparator<E> comp, boolean sortAscending)
	{
		//Calculate Interval
		int inter = 0;
		while (inter < ( arr.length / 3 ) + 1)
		{
			inter = inter * 3 + 1;
		}
		
		while (inter > 0)
		{
			for (int out = inter; out < arr.length; out++)
			{
				E value = arr[out];
				int in = out;
				
				if (sortAscending == true)
				{	
					while (in > inter - 1 && comp.compare(arr[in - inter], value) > 0)
					{
						arr[in] = arr[in - inter];
						in -= inter;
					}	
				}
				
				else
				{
					{	
						while (in > inter - 1 && comp.compare(arr[in - inter], value) < 0)
						{
							arr[in] = arr[in - inter];
							in -= inter;
						}	
					}
				}
				
				
				arr[in] = value;
			}
			
			inter = (inter - 1) /3;
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
