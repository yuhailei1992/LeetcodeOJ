package problems;
import java.util.Stack;

import problems.Datastructures.*;
public class Problems8 {
    public static int minDistance(String word1, String word2) {
        return 0;
    }

    public static void flatten(TreeNode root) {//non-recursive
        if (root == null) return;
        if (root.left == null && root.right == null) return;

        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode temp = root;
        TreeNode tempchild = null;
        while (true) {
            stk.push(temp.right);
            while (temp.left != null) {
                System.out.println(temp.val);
                stk.push(temp.right);
                temp.right = temp.left;
                temp.left = null;
                temp = temp.right;
                System.out.println("curr" + temp.val);
            }
            if (stk.isEmpty() == true) break;
            tempchild = stk.pop();
            while (tempchild == null && stk.isEmpty() == false) {
                tempchild = stk.pop();
            }
            if (tempchild != null) {
                temp.right = tempchild;
                temp = temp.right;
            }
        }
    }
    /**
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
     * @param root
     */
    public static void flatten_r (TreeNode root) {//recursive, AC, problem 81
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

    public static int divide(int dividend, int divisor) {//AC by consulting
        int ret = 0;
        long x = Math.abs((long)dividend);
        long y = Math.abs((long)divisor);

        while (x >= y) {
            int counter = 0;
            while (x >= (y << counter)) {
                counter++;
            }
            x -= y << (counter-1);
            ret += 1 << (counter-1);
        }

        if (dividend > 0 && divisor > 0) return ret;
        else if (dividend < 0 && divisor < 0) return ret;
        else return -ret;
    }


    public static void test () {
        /*
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        n0.right = n1;
        n1.left = n2;
        flatten(n0);
        TreeNode t = n0;
        while (t != null) {
        	System.out.println(t.val);
        	t = t.right;
        }
        */
        System.out.println(divide(-1010369383, -2147483648));
    }
}
