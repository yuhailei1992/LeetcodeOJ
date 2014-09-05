package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import problems.Datastructures.TreeNode;

public class Problems2 {
	public static int maxProfit(int[] prices) {//passed after consulting the answer
        int len = prices.length;
        if (len < 1) {
        	return 0;
        }
        int p = 0;
        for (int i = 1; i < len; ++i) {
        	if (prices[i] > prices[i - 1]) {
        		p += (prices[i] - prices[i - 1]);
        	}
        }
        return p;
    }
	
	public int numTrees(int n) {//passed after halfly consulting the answer
        if (n == 0) {
            return 1;
        }
        else if (n < 3) {
            return n;
        }
        else {
            int retval = 0;
            for (int i = 0; i < n; ++i) {
                retval += numTrees(i) * numTrees(n - 1 - i);
            }
            return retval;
        }
    }
	
	public List<Integer> preorderTraversal(TreeNode root) {//passed after consulting
        Stack<TreeNode> stk = new Stack<TreeNode>();
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (root == null) {
            return retlist;
        }
        else {
            TreeNode p = root;
            while (p != null || !stk.isEmpty()) {
                if (p != null) {
                    retlist.add(p.val);
                    stk.push(p);
                    p = p.left;
                }
                else {//p is null, meaning that we have reached the leftmost child. So we need to go back
                    TreeNode temp = stk.pop();
                    p = temp.right;
                }
            }
        }
        return retlist;
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {//passed
        Stack<TreeNode> stk = new Stack<TreeNode>();
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (root == null) {
            return retlist;
        }
        else {
            TreeNode p = root;
            while (p != null || !stk.isEmpty()) {
                if (p != null) {
                    stk.push(p);
                    p = p.left;
                }
                else {//p is null, meaning that we have reached the leftmost child. So we need to go back
                    TreeNode temp = stk.pop();
                    retlist.add(temp.val);
                    p = temp.right;
                }
            }
        }
        return retlist;
    }
}
