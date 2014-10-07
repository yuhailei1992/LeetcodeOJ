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

    /**Test case
        TreeLinkNode n0 = new TreeLinkNode(2);
    	TreeLinkNode n1 = new TreeLinkNode(1);
    	TreeLinkNode n2 = new TreeLinkNode(3);
    	TreeLinkNode n3 = new TreeLinkNode(0);
    	TreeLinkNode n4 = new TreeLinkNode(7);
    	TreeLinkNode n5 = new TreeLinkNode(9);
    	TreeLinkNode n6 = new TreeLinkNode(1);
    	TreeLinkNode n7 = new TreeLinkNode(2);
    	TreeLinkNode n8 = new TreeLinkNode(1);
    	TreeLinkNode n9 = new TreeLinkNode(0);
    	TreeLinkNode n10 = new TreeLinkNode(8);
    	TreeLinkNode n11 = new TreeLinkNode(8);
    	n0.left = n1;
    	n0.right = n2;
    	n1.left = n3;
    	n1.right = n4;
    	n2.left = n5;
    	n2.right = n6;
    	n3.left = n7;
    	n4.left = n8;
    	n4.right = n9;
    	n6.left = n10;
    	n6.right = n11;
    	connect(n0);
     */

    /**
     * The order of connect(right) and connect(left) is very important!
     * connect right first to ensure the connectivity.
     * @param root
     */
    public static void connect(TreeLinkNode root) {//AC.
        if (root == null) return;
        System.out.println(root.val);
        TreeLinkNode t = root.next;
        if (t == null) {
            System.out.println("null");
        }
        else {
            System.out.println("next is " + t.val);
        }
        if (root.left == null && root.right == null) return;
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            }
            else {
                TreeLinkNode p = root.next;
                while (p != null) {
                    if (p.left != null) {
                        root.left.next = p.left;
                        break;
                    }
                    else if (p.right != null) {
                        root.left.next = p.right;
                        break;
                    }
                    else p = p.next;
                }
                if (p == null) root.left.next = null;
            }
        }

        if (root.right != null) {
            TreeLinkNode p = root.next;
            while (p != null) {
                if (p.left != null) {
                    root.right.next = p.left;
                    break;
                }
                else if (p.right != null) {
                    root.right.next = p.right;
                    break;
                }
                else p = p.next;
            }
            if (p == null) root.right.next = null;
        }
        connect(root.right);
        connect(root.left);
    }

    /**test case
        List<List<Integer>> t = new ArrayList<List<Integer>>();
    	List<Integer> t1 = new ArrayList<Integer>();
    	List<Integer> t2 = new ArrayList<Integer>();
    	List<Integer> t3 = new ArrayList<Integer>();
    	List<Integer> t4 = new ArrayList<Integer>();
    	t1.add(2);
    	t.add(t1);
    	t2.add(3);
    	t2.add(4);
    	t.add(t2);
    	t3.add(6);
    	t3.add(5);
    	t3.add(7);
    	t.add(t3);
    	t4.add(4);
    	t4.add(1);
    	t4.add(8);
    	t4.add(7);
    	t.add(t4);
    	ListIterator<List<Integer>> iter = t.listIterator();
    	while (iter.hasNext()) {
    		showList(iter.next());
    	}
    	minimumTotal(t);
     */
    /**
     * use a helper array to store the temporal minimumTotal. A little tricky.
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {//AC
        int layer = triangle.size();
        int temp[] = new int[layer];
        for (int i = 0; i < layer; ++i) {
            temp[i] = triangle.get(layer-1).get(i);
        }
        for (int i = layer-1; i > 0; --i) {
            List<Integer> l1 = triangle.get(i-1);
            for (int j = 0; j < l1.size(); ++j) {
                temp[j] = Math.min(temp[j], temp[j+1]) + l1.get(j);
            }
        }
        return temp[0];
    }

    /**
     * post order traversal
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) {
            return arrlst;
        }
        if (root.left != null)
            arrlst.addAll(postorderTraversal(root.left));
        if (root.right != null)
            arrlst.addAll(postorderTraversal(root.right));
        arrlst.add(root.val);
        return arrlst;
    }

    public static List<Integer> postorderTraversalnonrecursive(TreeNode root) {
        ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) {
            return arrlst;
        }
        Stack<TreeNode> stk = new Stack<TreeNode>();
        while (true) {
            while (root != null) {
                arrlst.add(root.val);
                stk.push(root);
                root = root.left;
            }
            if (stk.isEmpty()) break;
            root = stk.pop();
            root = root.right;
        }
        return arrlst;
    }

    /**
     * consulted the solution of lei zhang.
     * His original solution was written in C++, and was incredibly elegant.
     * By using ?:, the need to break into several cases is omitted.
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {//AC after consulting
        if (a == null) return b;
        if (b == null) return a;
        String ret = "";
        int carry = 0;
        for (int i = a.length()-1, j = b.length()-1; i >=0 || j >= 0; --i, --j) {
            int tempa = i>=0 ? a.charAt(i)-'0': 0;
            int tempb = j>=0 ? b.charAt(j)-'0': 0;
            int temp = tempa + tempb + carry;
            carry = temp/2;
            ret += temp%2;
        }
        if (carry == 1) ret += '1';
        String reverse = new StringBuffer(ret).reverse().toString();
        //System.out.println(ret);
        return reverse;
    }

    public static boolean isValidSudoku(char[][] board) {//AC
        if (board == null) return false;
        //check the horizontal ones
        java.util.Hashtable<Character, Integer> table = new java.util.Hashtable<Character, Integer>();

        //check the vertical ones
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (table.containsKey(board[i][j]) && board[i][j] != '.') return false;
                table.put(board[i][j], 1);
            }
            table.clear();
        }
        for (int j = 0; j < 9; ++j) {
            for (int i = 0; i < 9; ++i) {
                if (table.containsKey(board[i][j]) && board[i][j] != '.') return false;
                table.put(board[i][j], 1);
            }
            table.clear();
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int m = i*3; m < i*3+3; ++m) {
                    for (int n = j*3; n < j*3+3; ++n) {
                        if (table.containsKey(board[m][n]) && board[m][n] != '.') return false;
                        table.put(board[m][n], 1);
                    }
                }
                table.clear();
            }
        }
        //check the subSudoku

        return true;
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
        String a = "10111";
        String b = "110111";
        addBinary(a, b);
    }
}
