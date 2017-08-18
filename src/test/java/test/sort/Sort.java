package test.sort;

import java.util.Iterator;

import org.junit.Test;

public class Sort {
	
	/**
	 * 从大到小排序 ，冒泡排序
	 */
	@Test
	public void sort1() {
		int arr[] = {-5, 29, 7, 10,5 ,16 };
		int mid ;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length-i-1; j++) {
				if(arr[j]<arr[j+1]) {
					mid = arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=mid;
				}
			}
		}
		for (int i : arr) {
			System.out.print(" "+i+" ");
		}
	}
}
