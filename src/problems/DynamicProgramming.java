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
	
	public static void LIS (int num[]) {//lis, O(n^2)
		
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
		System.out.println(maxlen[num.length - 1]);
	}
	
	public static void LISbetter (int num[]) {//use additional array to store the end of 
		//the sequence of current length. Use sequential search to find the position
		//to insert.
		int temp[] = new int [num.length];
		int maxlen = 1;
		temp[0] = num[0];
		for (int i = 1; i < num.length; ++i) {//i is index of num
			int j = 0;
			while (j < maxlen && num[i] > temp[j]) {//j is index of temp
				++j;
			}
			if (j >= maxlen) maxlen++;
			temp[j] = num[i];
		}
		System.out.println(maxlen);
	}
	
	public static void LISevenbetter (int num[]) {
		int temp[] = new int [num.length];
		int maxlen = 1;
		temp[0] = num[0];
		for (int i = 1; i < num.length; ++i) {//i is index of num
			if (num[i] > temp[maxlen - 1]) {
				temp[maxlen] = num[i];
				maxlen++;
			}
			else {
				int bottom = 0;
				int top = maxlen - 1;
				int mid = 0;
				while (top > bottom) {
					mid = (top + bottom) / 2;
					if (num[i] > temp[mid]) {
						bottom = mid + 1;
					}
					else {
						top = mid - 1;
					}
				}
				
				temp[mid] = num[i];
			}
		}
		System.out.println(maxlen);
	}
}
