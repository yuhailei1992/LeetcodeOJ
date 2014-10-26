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
    
    public static List<List<Integer>> fourSum(int[] num, int target) {
    	if (num == null) return null;
        
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num.length < 4) return ret;
        Arrays.sort(num);
        for (int i = 0; i < num.length-3; ++i) {
        	List<List<Integer>> ret3 = threeSum(num, -num[i], i+1, num.length-1);
        	if (ret3.size() != 0) {
        		for (int j = 0; j < ret3.size(); ++j) {
        			List<Integer> temp = new ArrayList<Integer>();
        			temp.add(num[i]);
        			temp.addAll(ret3.get(j));
        			ret.add(temp);
        		}
        	}
        	
        }
        return ret;
    }
    

    private static List<List<Integer>> threeSum(int[] num, int target, int left, int right) {//AC
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (right-left+1 < 3) return ret;
        
        loop:
        for (int i = left; i <= right-2; ++i) {
        	int start = i+1;
        	int end = right;
        	while (end > start) {
        	    if(i==0 || num[i]>=num[i-1]){
            		if (num[start] + num[end] + num[i] == target) {
            			ArrayList<Integer> temp = new ArrayList<Integer>();
            			temp.add(num[i]);
            			temp.add(num[start]);
            			temp.add(num[end]);
            			
            			ret.add(temp);
            			
            			while (start < end && num[end] == num[end-1]) end--;
            			while (start < end && num[start] == num[start+1]) start++;
            			
            			start++;
            			end--;
            			continue;
            		}
            		else if (num[start] + num[end] + num[i] > target) {
            			end--;
            		}
            		else {
            			start++;
            		}
        	    }
        	    else {
        	        continue loop;
        	    }
        	}
        }
        
        return ret;
    }
    
    public static List<List<Integer>> fourSum2(int[] num, int target) {//AC
        
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num.length < 4) return ret;
        Arrays.sort(num);
        int last_i = -1;
        for (int i = 0; i < num.length-3; ++i) {
        	if (last_i >= 0 && num[i] == num[last_i]) {
        		continue;
        	}
        	int last_j = -1;
        	for (int j = i+1; j < num.length-2; ++j) {
        		if (last_j >= 0 && num[j] == num[last_j]) {
        			continue;
        		}
        		int start = j+1;
        		int end = num.length-1;
        		while (end > start) {
        			int sum = num[start] + num[end] + num[i] + num[j];
        			if (sum == target) {
        				ArrayList<Integer> temp = new ArrayList<Integer>();
        				temp.add(num[i]);
        				temp.add(num[j]);
        				temp.add(num[start]);
        				temp.add(num[end]);
        				ret.add(temp);
        				System.out.println(temp);
        				last_i = i;
        				last_j = j;
        				while (start < end && num[end] == num[end-1]) end--;
            			while (start < end && num[start] == num[start+1]) start++;
            			start++;
            			end--;
            			continue;
        			}
        			else if (sum > target) {
            			end--;
            		}
            		else {
            			start++;
            		}
        		}
        	}
        }
        
        return ret;
    }
    	
    public static void test () {
    	int num2[] = {-3, -2, -1, 0, 0, 1, 2, 3};
    	int num[] = {-1, 0, -5, -2, -2, -4, 0, 1, -2};
    	System.out.println(fourSum2(num, -9));
    }
}
