package problems;

public class Backtrack {
	//n-queens problem:
	private static boolean isValidQ (int num[], int n) {
    	if (n >num.length) return false;
    	for (int i = 0; i < n; ++i) {
    		if (Math.abs(num[n] - num[i]) == n-i || num[n] == num[i]) return false;
    	}
    	return true;
    }
    
    public static void enumerate(int N) {
        int[] a = new int[N];
        enumerate(a, 0);
    }
    
    public static void enumerate(int[] q, int n) {
    	int N = q.length;
        if (n == N) printQueens(q);
        else {
            for (int i = 0; i < N; i++) {
                q[n] = i;
                if (isValidQ(q, n)) enumerate(q, n+1);
            }
        }
    }
    
    public static void printQueens(int[] q) {
        int N = q.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (q[i] == j) System.out.print("Q ");
                else           System.out.print("* ");
            }
            System.out.println();
        }  
        System.out.println();
    }
    //n-queens end
    
    
}
