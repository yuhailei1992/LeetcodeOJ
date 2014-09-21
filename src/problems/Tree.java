package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import problems.Datastructures.TreeNode;

public class Tree {
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
	
	public static List<Integer> preorderTraversalnonrecursive(TreeNode root) {
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
}
