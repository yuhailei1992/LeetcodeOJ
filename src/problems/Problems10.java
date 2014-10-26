package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
    
    public static String simplifyPath(String path) {//AC
        String[] tokens = path.split("/");
        System.out.println("TOKENS START");
        for (int i = 0; i < tokens.length; ++i) {
        	System.out.println(tokens[i]);
        }
        System.out.println("TOKENS end");

        java.util.LinkedList<String> stk = new java.util.LinkedList<String>();
        for (int i = 0; i < tokens.length; ++i) {
        	if (tokens[i].length() == 0 || tokens[i].equals(".")) {
        		continue;
        	}
        	else if (tokens[i].equals("..")) {
        		if (!stk.isEmpty()) {
        			stk.removeLast();
        		}
        		else {
        			//return "/";
        		}
        	}
        	else {
        		stk.add(tokens[i]);
        	}
        }
        if (stk.isEmpty()) {
        	return "/";
        }
        StringBuilder s = new StringBuilder();
        while (!stk.isEmpty()) {
        	s.append("/");
        	s.append(stk.remove());
        }
        return s.toString();
    }
    
    public static List<List<Integer>> subsets(int[] S) {//AC
        List<List<Integer>> ret = new ArrayList<List<Integer>> ();
        if (S.length == 1) {
        	List<Integer> temp0 = new ArrayList<Integer>();
        	List<Integer> temp1 = new ArrayList<Integer>();
        	temp1.add(S[0]);
        	ret.add(temp0);
        	ret.add(temp1);
        	return ret;
        }
        else {
        	Arrays.sort(S);
            int len = S.length;
            int temp[] = Arrays.copyOf(S, len-1);
            List<List<Integer>> subret = subsets(temp);
            System.out.println(subret);
            for (List<Integer> i : subret) {
            	System.out.println("1" + i);
            	ret.add(i);
            }
            for (List<Integer> j : subret) {
            	List<Integer> i2 = new ArrayList<Integer>(j);
            	i2.add(S[len-1]);
            	System.out.println("2" + j);
            	ret.add(i2);
            }
            return ret;
        }
        
    }
    
    
    public static int maximalRectangle(char[][] matrix) {
        return 0;
    }
    
    public static void test () {
    	int a[] = {1, 2};
    	System.out.println("final" + subsets(a));
    }
}
