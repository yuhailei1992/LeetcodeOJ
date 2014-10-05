package problems;
import java.util.Stack;

import problems.Datastructures.*;
public class Problems8 {
	public static int minDistance(String word1, String word2) {
       return 0; 
    }
	
	public static void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode temp = root;
        
        while (true) {
        	while (temp != null) {
	        	System.out.println(temp.val);
	        	stk.push(temp);
	        	temp = temp.left;
        	}
        	if (stk.isEmpty() == true) break;
        	temp = stk.pop();
        	temp = temp.right;
        }
    }
	
	public static void flatten_r (TreeNode root) {//recursive, AC
		if (root == null) return;
		sub_flat(root);
	}
	
	public static TreeNode sub_flat (TreeNode root) {
		if (root == null) return null;
		else if (root.left == null && root.right == null) return root;
		else {
			TreeNode tempr = sub_flat(root.right);
			root.right = sub_flat(root.left);
			root.left = null;
			TreeNode temp = root;
			while (temp.right != null) {
				temp = temp.right;
			}
			temp.right = tempr;
			return root;
		}
	}
	
	public static void test () {
		TreeNode n0 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(5);
		TreeNode n5 = new TreeNode(6);
		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		flatten_r(n0);
		TreeNode t = n0;
		while (t != null) {
			System.out.println(t.val);
			t = t.right;
		}
	}
}
