package SortAlgorithms;

import java.util.Random;

public class BubbleSortAlgorithm {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] numbers = new int[10];
		for(int i = 0 ; i < numbers.length ; i++) {
			numbers[i] = rand.nextInt(100);
		}
		
		System.out.println(numbers);
		
		//Sort(numbers);
		//System.out.println(Arrays.toString(numbers));
	}

	//private static void Sort(int[] numbers) {
		
		
	//}

}