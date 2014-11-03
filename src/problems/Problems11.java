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
    
    public static int largestRectangleArea(int[] height) {//AC
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
    
    public static void solve(char[][] board) {//AC
    	if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; ++i) {
        	if (board[i][0] == 'O') {
        		visited[i][0] = 1;
        	}
        	if (board[i][n-1] == 'O') {
        		visited[i][n-1] = 1;
        	}
        }
        
        for (int j = 0; j < n; ++j) {
        	if (board[0][j] == 'O') {
        		visited[0][j] = 1;
        	}
        	if (board[m-1][j] == 'O') {
        		visited[m-1][j] = 1;
        	}
        }
        
        //then, dfs
        for (int i = 0; i < m; ++i) {
        	if (visited[i][0] == 1) {
        		dfs(board, visited, i, 0);
        	}
        	if (visited[i][n-1] == 1) {
        		dfs(board, visited, i, n-1);
        	}
        }
        
        for (int j = 0; j < n; ++j) {
        	if (visited[0][j] == 1) {
        		dfs(board, visited, 0, j);
        	}
        	if (visited[m-1][j] == 1) {
        		dfs(board, visited, m-1, j);
        	}
        }
        
        for (int i = 0; i < m; ++i) {
        	for (int j = 0; j < n; ++j) {
        		if (visited[i][j] == 0 && board[i][j] == 'O') {
        			board[i][j] = 'X';
        		}
        	}
        }
        for (int i = 0; i < m; ++i) {
        	for (int j = 0; j < n; ++j) {
        		System.out.print(board[i][j]);
        	}
        	System.out.println();
        }
        return;
        
    }
    
    private static void dfs(char[][] board, int[][]visited, int row, int col) {
    	if (visited[row][col] == 0) visited[row][col] = 1;
    	if (row > 0 && board[row-1][col] == 'O' && visited[row-1][col] == 0) {
    		dfs (board, visited, row-1, col);
    	}
    	if (row < board.length-1 && board[row+1][col] == 'O' && visited[row+1][col] == 0) {
    		dfs (board, visited, row+1, col);
    	}
    	if (col > 0 && board[row][col-1] == 'O' && visited[row][col-1] == 0) {
    		dfs (board, visited, row, col-1);
    	}
    	if (col < board[0].length-1 && board[row][col+1] == 'O' && visited[row][col+1] == 0) {
    		dfs (board, visited, row, col+1);
    	}
    }
    
    public static String longestPalindrome(String s) {
        return null;
    }
    
    public static int longestValidParentheses (String s) {//AC
    	if (s == null || s.length() < 2) return 0;
    	int maxlen = 0;
    	int last = -1;
    	int i = 0;
    	Stack<Integer> stk = new Stack<Integer>();
    	while (i < s.length()) {
    		char curr = s.charAt(i);
    		if (curr == '(') {
    			stk.push(i);
    		}
    		else {
    			
    			if (stk.isEmpty()) {
    				last = i;
    			}
    			else {
    				stk.pop();
    				if (stk.isEmpty()) {
    					maxlen = Math.max(maxlen, i - last);
    				}
    				else {
        				maxlen = Math.max(maxlen, i - stk.peek());
    				}
    			}
    		}
    		++i;
    	}
    	return maxlen;
    }
    
    public static int canCompleteCircuit(int[] gas, int[] cost) {//naive, TLE
        //the result is guaranteed to be unique
    	//for those problem with "circular", we can gracefully solve it by doubling the length
    	int len = gas.length;
    	for (int i = 0; i < len; ++i) {
    		int total_gas = 0;
    		
    		for (int j = i; j <= len + i; ++j) {
    			if (j == len+i) return i;
    			total_gas += gas[j%len];
    			if (total_gas < (cost[j%len])) {
    				break;
    			}
    			else {
    				total_gas -= (cost[j%len]);
    			}
    			
    		}
    	}
    	return -1;
    }
    
    public static int canCompleteCircuit2(int[] gas, int[] cost) {//AC
        int len = gas.length;
    	for (int i = 0; i < len; ++i) {
    		int total_gas = 0;
    		
    		for (int j = i; j <= len + i; ++j) {
    			if (j == len+i) return i;
    			total_gas += gas[j%len];
    			if (total_gas < (cost[j%len])) {
    			    i = j;
    				break;
    			}
    			else {
    				total_gas -= (cost[j%len]);
    			}
    		}
    	}
    	return -1;
    }
    
    public static int findMin(int[] num) {//AC
        int len = num.length;
        int min = num[0];
        int left = 0;
        int right = len-1;
        while (left < right) {
            int mid = (left + right) / 2;
            min = Math.min(Math.min(num[left], num[right]), Math.min(num[mid], min));
            if (num[mid] < num[right]) {
                //search left portion
                right = mid-1;
            }
            else {
                //search right portion
                left = mid+1;
            }
            
        }
        return min;
    }
    
    public static void test () {
    	int gas[] = {2, 4};
    	int cost[] = {3, 4};
    	System.out.println(canCompleteCircuit(gas, cost));
    }
}
