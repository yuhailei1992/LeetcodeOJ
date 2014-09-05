package problems;

public class Datastructures {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
		    val = x;
		    next = null;
		}
	}
	
	public static class TreeNode {//binary tree node
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}
	
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
}
