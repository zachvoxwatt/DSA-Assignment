package sorting;

import java.util.Comparator;

import geom.Point2D;

//Bubble Sort is the worst sort because it's overall Time Complexity is O(N^2) as 2 loops have the worse case
//to execute being O(n)

public class BubbleSort<E> implements ISort<E> 
{
	@Override
	public void sort(E[] arr, Comparator<E> comp, boolean sortAscending)
	{
		int curr = 0;
		boolean alert = false;
		
		while (curr < arr.length && alert == false)
		{
			int itor = arr.length - 1;
			alert = true;
			
			while (curr < itor)
			{
				if (sortAscending == true)
				{
					if ( comp.compare(arr[itor], arr[itor - 1]) < 0)
					{
						alert = false;
						
			//If a teacher found this comment, I have so many question to ask
			// manually declaring a temporary generic variable and swapping between 2 targets works
			//whilst creating a discrete method doesn't
						
						E tmp;
						
						tmp = arr[itor];
						arr[itor] = arr[itor - 1];
						arr[itor - 1] = tmp;
			
					//This swap() method doesn't work although I implemented correct parameters
			//			swap(arr[itor], arr[itor - 1]);
					}
					
					itor--;
				}
				
				else
				{
					if ( comp.compare(arr[itor], arr[itor - 1]) > 0)
					{
						alert = false;
						
			//If a teacher found this comment, I have so many question to ask
			// manually declaring a temporary generic variable and swapping between 2 targets works
			//whilst creating a discrete method doesn't
						
						E tmp;
						
						tmp = arr[itor];
						arr[itor] = arr[itor - 1];
						arr[itor - 1] = tmp;
			
					//This swap() method doesn't work although I implemented correct parameters
			//			swap(arr[itor], arr[itor - 1]);
					}
					
					itor--;
				}
				
				
			}
			
			curr++;
		}
	}
	
	public void swap(E a, E b)
	{
		E tmp;
		
		tmp = a;
		a = b;
		b = tmp;
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