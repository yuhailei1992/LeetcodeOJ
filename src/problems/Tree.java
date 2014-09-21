package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import problems.Datastructures.TreeNode;

public class Tree {
	
	//recursive solutions
	
	public static List<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) {
        	return arrlst;
        }
        arrlst.add(root.val);
        if (root.left != null)
        	arrlst.addAll(preorderTraversal(root.left));
        if (root.right != null)
        	arrlst.addAll(preorderTraversal(root.right));
        
        return arrlst;
    }
	
	public static List<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) {
        	return arrlst;
        }
        if (root.left != null)
        	arrlst.addAll(inorderTraversal(root.left));
        arrlst.add(root.val);
        if (root.right != null)
        	arrlst.addAll(inorderTraversal(root.right));
        return arrlst;
    }
	
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
	
	//iterative solutions:
	
	public static List<Integer> preorderTraversalnonrecursive (TreeNode root) {
		ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) return arrlst;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (stk.isEmpty() == false) {
        	TreeNode temp = stk.pop();
        	arrlst.add(temp.val);
        	if (temp.right != null) stk.push(temp.right);
        	if (temp.left != null) stk.push(temp.left);
        }
        return arrlst;
	}
	
	public static List<Integer> preorderTraversalnonrecursive2 (TreeNode root) {
		ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) return arrlst;
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
	
	public static List<Integer> inorderTraversalnonrecursive (TreeNode root) {
		ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) return arrlst;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        while (stk.isEmpty() == false || root != null) {
        	if (root != null) {
        		stk.push(root);
        		root = root.left;
        	}
        	else {
        		root = stk.pop();
        		arrlst.add(root.val);
        		root = root.right;
        	}
        }
        return arrlst;
	}
	
	public static List<Integer> postorderTraversalnonrecursive (TreeNode root) {
		ArrayList<Integer> arrlst = new ArrayList<Integer>();
        if (root == null) return arrlst;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        Stack<TreeNode> stk2 = new Stack<TreeNode>();
        stk.push(root);
        while (stk.isEmpty() == false) {
        	TreeNode temp = stk.pop();
        	stk2.push(temp);
        	if (temp.left != null) stk.push(temp.left);
        	if (temp.right != null) stk.push(temp.right);
        }
        while (stk2.isEmpty() == false) {
        	arrlst.add(stk2.pop().val);
        }
        return arrlst;
	}
	
	//level order traversal:
	public static List<Integer> levelorderTraversal(TreeNode root) {
		return null;
	}
	
	
	
	public static void test () {
		TreeNode n0 = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		n0.right = n1;
		n1.left = n2;
		Helpers.showList(postorderTraversal(n0));
		Helpers.showList(postorderTraversalnonrecursive(n0));
	}
}
