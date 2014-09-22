package problems;

import java.util.LinkedList;
import java.util.*;

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
	/**
	 * implemented levelOrderTraversal with a queue
	 * @param root
	 * @return
	 */
	public static void levelOrderTraversal(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> q = new java.util.LinkedList<TreeNode>();
		q.add(root);
		while (q.isEmpty() == false) {
			TreeNode temp = q.element();
			System.out.println(temp.val);
			q.remove();
			if (temp.left != null) q.add(temp.left);
			if (temp.right != null) q.add(temp.right);
		}
	}
	
	public static void levelOrderTraversal2 (TreeNode root) {
		if (root == null) return;
		ArrayList<LinkedList<TreeNode>> q = new ArrayList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> l0 = new java.util.LinkedList<TreeNode>();
		LinkedList<TreeNode> l1 = new java.util.LinkedList<TreeNode>();
		l0.add(root);
		q.add(l0);
	}
	
	//public static void 
	
	public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
		return null;
	}
	
	public static void test () {
		TreeNode n0 = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(5);
		n0.right = n1;
		n0.left = n3;
		n1.left = n2;
		n1.right = n4;
		//levelOrderBottom(n0);
		//Helpers.showList(levelOrderBottom(n0));
		//Helpers.showList(postorderTraversalnonrecursive(n0));
	}
}
