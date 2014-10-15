package problems;

public class SortingAlgorithm {
    /**
     * merge sort
     * quick sort
     * heap sort
     * selection sort
     * bubble sort
     * insertion sort
     */
	
	//merge sort
	public static void mergeSort (int[] A) {
		//corner cases
		if (A == null) return;
		int len = A.length;
		if (len < 2) return;
		//general cases
		subMerge(A, 0, len-1);
	}
	public static void subMerge (int[] A, int start, int end) {
		if (end - start <= 1) return;
		else if (end - start <= 2) {
			//placeholder
		}
		else {
			int mid = (start + end) / 2;
			subMerge(A, start, mid);
			subMerge(A, mid + 1, end);
		}
		
	}
	
	public static void test() {
		
	}
}
