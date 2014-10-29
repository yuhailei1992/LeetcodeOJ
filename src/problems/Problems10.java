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
    
    private static boolean bfs(char[][] board, String word, int row, int col) {
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
    
    /**
     * Some examples:
	 * "0" => true
	 * "0.1" => true
	 * "abc" => false
	 * "1 a" => false
	 * "2e10" => true
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
    	if (s == null) return false;
    	String str = s.trim();
    	int len = str.length();
    	if (len == 0) return false;
    	int exp = 0;
    	//can only begin with numbers
    	if (len == 1 && !(str.charAt(0) >= '0' && str.charAt(0) <= '9')) return false;
    	if (!(str.charAt(0) >= '0' && str.charAt(0) <= '9') && str.charAt(0) != '.') {
    		return false;
    	}
    	for (int i = 1; i < str.length(); ++i) {
    		char curr = str.charAt(i);
    		if (curr == ' ') {
    			return false;
    		}
    		else if (curr == 'e') {
    			exp = 1;
    			if (i == len-1) return false;
    			else {
    				if (!(str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9')) {
    					return false;
    				}
    			}
    		}
    		else if (curr == '.') {
    			if (exp == 1) return false;
    			// you cannot have two consecutive '.'
    		    if (i != 0 && str.charAt(i-1) == '.') return false;
    		    // 
    			if ( i != len-1 && (!(str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9'))) {
    				return false;
    			}
    		}
    	}
        return true;
    }
    
    public static int maximalRectangle(char[][] matrix) {
        return 0;
    }
    
    public static String getPermutation(int n, int k) {
        return null;
    }
    
    public static void solveSudoku(char[][] board) {//AC
        dfs(board, 0, 0);
    }
    
    private static boolean isValid (char[][] board, int x, int y, char k) {
    	//check row
    	for (int i = 0; i < 9; ++i) {
    		if (board[i][y] == k && i != x) return false;
    	}
    	//check column
    	for (int j = 0; j < 9; ++j) {
    		if (board[x][j] == k && j != y) return false;
    	}
    	//check 3*3 rectangles
    	for (int i = (x/3)*3; i < (x/3+1)*3; ++i) {
    		for (int j = (y/3)*3; j < (y/3+1)*3; ++j) {
    			if (board[i][j] == k && i != x && j != y) return false;
    		}
    	}
    	return true;
    }
    
    private static boolean dfs(char[][] board, int x, int y) {
    	if (x == 9 || y == 9) return true;
    	
    	if (board[x][y] == '.') {
    		for (int k = 1; k <= 9; ++k) {
    			if (isValid(board, x, y, (char)('0'+k))) {
    				board[x][y] = (char)('0'+k);
    				int nextx = x;
    				int nexty = y+1;
    				if (nexty == 9) {
    					nexty = 0; 
    					nextx++;
    				}
    				if (dfs(board, x, y)) return true;
    			}
    			board[x][y] = '.';
    		}
    		return false;
    	}
    	else {
    		int nextx = x;
			int nexty = y+1;
			if (nexty == 9) {
				nexty = 0; 
				nextx++;
			}
			return dfs(board, nextx, nexty);
    	}
    }
    
    public static void test () {
    	char[][] board = {  
                {'.','.','9','7','4','8','.','.','.'},  
                {'7','.','.','.','.','.','.','.','.'},  
                {'.','2','.','1','.','9','.','.','.'},  
                {'.','.','7','.','.','.','2','4','.'},  
                {'.','6','4','.','1','.','5','9','.'},  
                {'.','9','8','.','.','.','3','.','.'},  
                {'.','.','.','8','.','3','.','2','.'},  
                {'.','.','.','.','.','.','.','.','6'},  
                {'.','.','.','2','7','5','9','.','.'},  
        };
    	solveSudoku(board);
    	for(int i=0; i<9; i++){  
            for(int j=0; j<9; j++){  
                System.out.print(board[i][j]);  
            }  
            System.out.println();  
        }
    }
}
