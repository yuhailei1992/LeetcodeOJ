package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problems10 {
    public static double findMedianSortedArrays(int A[], int B[]) {
        return 0.0;
    }
    
    public static int sqrt(int x) {
        if (x < 0) return -1;
        else if (x < 2) return x;

        long xt = (long)x;
        int low = 1;
        int high = x/2+1;
        int mid = 0;
        while (high >= low) {
            mid = low + (high - low)/2;
            long midt = (long)mid * (long)mid;
            if (midt < xt) low = mid+1;
            else high = mid-1;
            System.out.println(mid);
        }
        return mid;
    }
    
    public static int minDistance(String word1, String word2) {
        return 0;
    }
    
    public boolean exist(char[][] board, String word) {
    	
        return false;
    }
    
    public static int threeSumClosest(int[] num, int target) {//AC
        
        Arrays.sort(num);
        int min = Integer.MAX_VALUE;
        int min_sum = 0;
        loop:
        for (int i = 0; i < num.length-2; ++i) {
        	int start = i+1;
        	int end = num.length-1;
        	while (end > start) {
        	    if(i==0 || num[i]>num[i-1]){
        	    	int sum = num[start] + num[end] + num[i];
        	    	int diff = Math.abs(sum-target);
        			if (diff < min) {
        				min = diff;
        				min_sum = sum;
        			}
        			
            		if (sum == 0) {
            			return target;
            		}
            		else {
            			//get rid of the useless elements
            			if (sum > target) {
            				end--;
            			}
            			else {
            				start++;
            			}
            		}
        	    }
        	    else {
        	        continue loop;
        	    }
        	}
        }
        
        return min_sum;
    }
    
    public static void test () {
    	int num[] = {-1, 2, 1, -4};
    	System.out.println(threeSumClosest(num, 1));
    }
}
