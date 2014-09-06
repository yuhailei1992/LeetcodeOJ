package problems;

public class DynamicProgramming {
	public static void minNumCoin () {
		int m = 11;//total number
		int coin[] = {1, 3, 5};//different coins
		int min[] = new int[12];//stores the minimum number of coins for each price
		for (int i = 0; i <= m; ++i) {
			if (i < 3) {
				min[i] = i;
			}
			else {
				int x0 = i >= coin[0] ? min[i - coin[0]] : i - 1;//i - 1 is for cases where there is 1-dollar coin
				int x1 = i >= coin[1] ? min[i - coin[1]] : i - 1;
				int x2 = i >= coin[2] ? min[i - coin[2]] : i - 1;
				min[i] = Math.min(Math.min(x0, x1), x2) + 1;
			}
		}
		for (int i = 0; i <= m; ++i) {
			System.out.println(min[i]);
		}
	}
	
	public static void LIS () {//lis, O(n^2)
		int num[] = {5, 3, 4, 8, 6, 7};
		int maxlen[] = new int [num.length];
		for (int i = 0; i < num.length; ++i) {
			if (i == 0) {
				maxlen[i] = 1;
			}
			else {
				int tempmax = 1;
				for (int j = 0; j < i; ++j) {
					if (num[j] < num[i]) {
						if (maxlen[j] + 1 > tempmax) {
							tempmax = maxlen[j] + 1;
						}
					}
				}
				maxlen[i] = tempmax;
			}
		}
		for (int i = 0; i < num.length; ++i) {
			System.out.println(maxlen[i]);
		}
	}
	
	public static void LISbetter () {
		
	}
}
