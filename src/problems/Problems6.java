package problems;

public class Problems6 {
	/**
	 * The capacity of water that an element can hold is determined by the max height
	 * to the left and the max height to the right;
	 * To determine the actual capacity, we need to compare the value at current 
	 * element with the minimum of left and right max height;
	 * 
	 * Implementation:
	 * first, scan from left to right, store the max height to the left of current element;
	 * then, scan from right to left, store the max height to the right of current element;
	 * finally, scan from left to right, calculate the capacity on current element;
	 * 
	 * Note that we can calculate the capacity on the second scan, saving a scan
	 * time complexity: 2n, O(n);
	 * space complexity: 2n, O(n);
	 * @param A
	 * @return
	 */
	public static int trap(int[] A) {
		if (A == null || A.length < 2) return 0;
		int len = A.length;
		int sum = 0;
		int leftHeight[] = new int[len];
		int rightHeight[] = new int[len];
		int tempmax = 0;
		for (int i = 0; i < len; ++i) {
			leftHeight[i] = tempmax;
			if (A[i] > tempmax) {
				tempmax = A[i];
			}
		}
		showArray(leftHeight);
		tempmax = 0;
		for (int i = len-1; i >= 0; --i) {
			rightHeight[i] = tempmax;
			int line = Math.min(leftHeight[i], rightHeight[i]);
			if (A[i] < line) {
				sum += (line - A[i]);
			}
			//update tempmax
			if (A[i] > tempmax) {
				tempmax = A[i];
			}
		}
		System.out.println(sum);
		return sum;
    }
	
	public static void showArray (int[] n) {
		for (int i = 0; i < n.length; ++i) {
			System.out.print(n[i]);
		}
		System.out.println("end");
	}
	
	public static void test () {
		int A[] = {2, 2, 1, 0, 1, 0, 1, 2, 1};
		trap(A);
	}
}
