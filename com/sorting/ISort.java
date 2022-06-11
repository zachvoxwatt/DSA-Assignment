package sorting;

import java.util.Comparator;

import geom.Point2D;

public interface ISort<E> 
{
	public void sort (E[] arr, Comparator<E> comp, boolean sortAscending);
	public Point2D getCOM(Point2D[] list);
}
