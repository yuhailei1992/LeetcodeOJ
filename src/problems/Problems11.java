package problems;
import java.util.*;
import problems.Datastructures.*;

public class Problems11 {
    public static List<String> restoreIpAddresses(String s) {//AC
    	int len = s.length();
    	if (len < 4 || len > 12) return new ArrayList<String>();
        return subIP(s, 4);
    }
    
    private static ArrayList<String> subIP (String str, int n) {
    	ArrayList<String> ret = new ArrayList<String>();
    	if (str.length() == 0 || str.length() > n * 3) {
    		return ret;
    	}
    	else {
    		for (int i = Math.min(3, str.length()); i > 0; --i) {
    			String first_num = str.substring(0, i);
    			if (isValid(first_num) && str.length()-i <= 3 * (n-1)) {
    				
    				//check the rest
    				if (n-1 == 0) {
    					ret.add(first_num);
    				}
    				else {
	    				ArrayList<String> sub_ret = subIP(str.substring(i), n-1);
	    				if (sub_ret.size() != 0) {//valid return
	    					//System.out.println(sub_ret.size() + "n" + n);
	    					for (int j = 0; j < sub_ret.size(); ++j) {
	    						String temp = first_num + "." + sub_ret.get(j);
	    						ret.add(temp);
	    					}
	    				}
    				}
    			}
    		}
    	}
    	return ret;
    }
    
    private static boolean isValid (String str) {
    	if (str == null || str.length() == 0 || str.length() > 3) return false;
    	else {
    		if (str.length() > 1 && str.charAt(0) == '0') return false;
    		else if (Integer.parseInt(str) > 255) return false;
    		else return true;
    	}
    }
    
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	
        return null;
    }
    
    public int maximalRectangle(char[][] matrix) {//AC
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
    	int[][] num = new int[m][n];
    	for (int i = 0; i < m; ++i) {
    		for (int j = 0; j < n; ++j) {
    			if (matrix[i][j] == '1') {
    				num[i][j] = 1;
    			}
    			else {
    				num[i][j] = 0;
    			}
    		}
    	}
    	for (int i = 1; i < m; ++i) {
    		for (int j = 0; j < n; ++j) {
    			if (num[i][j] != 0) {
    				num[i][j] += num[i-1][j];
    			}
    		}
    	}
    	//now we have constructed the matrix
    	int max = -1;
    	for (int i = 0; i < m; ++i) {
    		int temp_max = largestRectangleArea(num[i]);
    		if (temp_max > max) {
    			max = temp_max;
    		}
    	}
        return max;
    }
    
    public static int largestRectangleArea(int[] height) {
        Stack<Integer> stk = new Stack<Integer>();
    	int h[] = new int[height.length + 1];
    	h = Arrays.copyOf(height, height.length+1);
    	int maxArea = 0;
    	int i = 0;
    	while (i < h.length) {
    		if (stk.isEmpty() || h[stk.peek()] <= h[i]) {
    			stk.push(i);
    			i++;
    		}
    		else {
    			int t = stk.pop();
    			maxArea = Math.max(maxArea, h[t] * (stk.isEmpty() ? i : i - stk.peek() - 1));
    		}
    	}
        return maxArea;
    }
    
    public static void test () {
    	char[][] x = new char[1][];
    	maximalRectangle(x);
    	
    }
}
