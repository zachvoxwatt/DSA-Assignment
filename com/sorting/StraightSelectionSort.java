package sorting;

import java.util.Comparator;

import geom.Point2D;

public class StraightSelectionSort<E> implements ISort<E>
{
	@Override
	public void sort(E[] arr, Comparator<E> comp, boolean sortAsc)
	{
		int curr = 0;
		
		while (curr < arr.length - 1)
		{
			int itor = curr + 1;
			int mark = curr;
			
			while (itor < arr.length)
			{
				if (sortAsc == true)
				{	
					if ( comp.compare(arr[mark], arr[itor]) > 0)
					{	
						mark = itor;
					}
				}
				
				else
				{
					if ( comp.compare(arr[mark], arr[itor]) < 0)
					{
						mark = itor;
					}
				}
				
				itor++;
			}
			
			if ( mark != curr)
			{
				E tmp;
				
				tmp = arr[mark];
				arr[mark] = arr[curr];
				arr[curr] = tmp;
			}
			
			curr++;
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
