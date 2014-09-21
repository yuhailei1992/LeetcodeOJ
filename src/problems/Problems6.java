package problems;
import java.util.*;

import problems.Helpers;
import problems.Datastructures.*;
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
		Helpers.showArray(leftHeight);
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

	/**
	 * This problem is very boring. 
	 * 
	 * To minimize the space usage, we need to use the first row and first column
	 * to store the "clear flag". Since the first row and column will be modified
	 * along the way, we need to set another two flags indicating if they should
	 * be cleared too. 
	 * 
	 * Implementation:
	 * 1, handle corner cases: matrix is null, row is 1, column is 1;
	 * 2, check the first row and column, see if they need to be cleared
	 * 3, traverse the whole matrix, set clear flags in the first row and col
	 * 4, traverse the whole matrix, clear 
	 * 5, clear the first row and column
	 * @param matrix
	 */
	public static void setZeroes(int[][] matrix) {//AC
		if (matrix == null) return;
		int row_zero = 1;
		int col_zero = 1;
		int row = matrix.length;
		int col = matrix[0].length;
		if (row == 1) {
			for (int i = 0; i < col; ++i) {
				if (matrix[0][i] == 0) {
					for (int j = 0; j < col; ++j) {
						matrix[0][j] = 0;
					}
					return;
				}
			}
			return;
		}
		
		if (col == 1) {
			for (int i = 0; i < row; ++i) {
				if (matrix[i][0] == 0) {
					for (int j = 0; j < row; ++j) {
						matrix[j][0] = 0;
					}
					return;
				}
			}
			return;
		}
		
		for (int i = 0; i < row; ++i) {
			if (matrix[i][0] == 0) {
				col_zero = 0;
				break;
			}
		}
		for (int i = 0; i < col; ++i) {
			if (matrix[0][i] == 0) {
				row_zero = 0;
				break;
			}
		}
		for (int i = 1; i < row; ++i) {
			for (int j = 1; j < col; ++j) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < row; ++i) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < col; ++j) {
					matrix[i][j] = 0;
				}
			}
		}
		for (int i = 1; i < col; ++i) {
			if (matrix[0][i] == 0) {
				for (int j = 1; j < row; ++j) {
					matrix[j][i] = 0;
				}
			}
		}
		if (row_zero == 0) {
			for (int i = 0; i < col; ++i) {
				matrix[0][i] = 0;
			}
		}
		if (col_zero == 0) {
			for (int i = 0; i < row; ++i) {
				matrix[i][0] = 0;
			}
		}
		return;
        
    }
	
	/**
	 * canonical problem using stack
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {//AC
        if (s == null) {
        	return true;
        }
        int len = s.length();
        if (len % 2 == 1) return false;
        char[] temp = s.toCharArray();
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < len; ++i) {
        	if (temp[i] == '(' || temp[i] == '[' || temp[i] == '{') {
        		stk.push(temp[i]);
        	}
        	else if (temp[i] == ')') {
        		if (stk.size() == 0) return false;//in case the string starts with ")"
        		if (stk.pop() != '(') return false;
        	}
        	else if (temp[i] == ']') {
        		if (stk.size() == 0) return false;
        		if (stk.pop() != '[') return false;
        	}
        	else if (temp[i] == '}') {
        		if (stk.size() == 0) return false;
        		if (stk.pop() != '{') return false;
        	}
        }
        if (stk.size() != 0) return false;
        return true;
    }

	/**
	 * similar to unique path
	 * two-dimensional DP
	 * uses no extra space
	 * @param obstacleGrid
	 * @return
	 */
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {//AC
		if (obstacleGrid == null) return 0;
		if (obstacleGrid[0][0] == 1) return 0;
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if (m == 1) {//only one row
			for (int i = 0; i < n; ++i) {
				if (obstacleGrid[0][i] == 1) {
					return 0;
				}
			}
			return 1;
		}
		if (n == 1) {//only one row
			for (int i = 0; i < m; ++i) {
				if (obstacleGrid[i][0] == 1) {
					return 0;
				}
			}
			return 1;
		}
		//regular content begin!
		for (int i = m-1; i > 0; --i) {
			if (obstacleGrid[i-1][n-1] == 0)
				obstacleGrid[i-1][n-1] = obstacleGrid[i][n-1] > 0 ? 1 : -1;
		}
		for (int i = n-1; i > 0; --i) {
			if (obstacleGrid[m-1][i-1] == 0)
				obstacleGrid[m-1][i-1] = obstacleGrid[m-1][i] > 0 ? 1 : -1;
		}
		for (int i = m-2; i >= 0; --i) {
			for (int j = n-2; j >= 0; --j) {
				int a1 = obstacleGrid[i+1][j] > 0 ? 0 : obstacleGrid[i+1][j];
				int a2 = obstacleGrid[i][j+1] > 0 ? 0 : obstacleGrid[i][j+1];
				obstacleGrid[i][j] = obstacleGrid[i][j] > 0 ? 1 : a1 + a2;
			}
		}
		return (-1) * obstacleGrid[0][0];
    }
	
	/**
	 * shuizhongdeyu provided a better solution with only one traversal of the list
	 * 
	 * @param head
	 * @return
	 */
	public static TreeNode sortedListToBST(ListNode head) {//AC
        if (head == null) {
        	return null;
        }
        ListNode p = head;
        int len = 0;
        while (p != null) {
        	len++;
        	p = p.next;
        }
        int num[] = new int[len];
        p = head;
        len = 0;
        while (p != null) {
        	num[len] = p.val;
        	len++;
        	p = p.next;
        }
        return buildTree(num, 0, len-1);
    }
	
	public static TreeNode buildTree (int[] num, int start, int end) {
		if (start > end) return null;
		if (start == end) {
			TreeNode node = new TreeNode(num[start]);
			return node;
		}
		int mid = (start + end)/2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = buildTree(num, start, mid-1);
		node.right = buildTree(num, mid+1, end);
		return node;
	}

	public static int minimumTotal(List<List<Integer>> triangle) {
		//if (triangle == null) return 0;
		int level = triangle.size();
        List<Integer> t = triangle.get(level-1);
        showList(t);
        return 0;
    }

	
	public static void showList (List<Integer> l) {
		int len = l.size();
		System.out.println("size is " + len);
		for (int i = 0; i < len; ++i) {
			System.out.print(l.get(i));
		}
		System.out.println();
	}
	
	public static void test () {
		//int a[][] = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
		int a[][] = new int[][]{{0, 0}, {1, 0}};
		uniquePathsWithObstacles(a);
		/*List<List<Integer>> t = new ArrayList<List<Integer>>();
		List<Integer> t1 = new ArrayList<Integer>();
		t1.add(2);
		t.add(t1);
		t1.clear();
		t1.add(3);
		t1.add(4);
		t.add(t1);
		t1.clear();
		t1.add(6);
		t1.add(5);
		t1.add(7);
		t.add(t1);
		t1.clear();
		t1.add(4);
		t1.add(1);
		t1.add(8);
		t1.add(7);
		t.add(t1);
		t1.clear();
		minimumTotal(t);
		showList(t.get(2));*/
	}
}
