package problems;
import problems.Datastructures.TreeNode;

public class Problems4 {
	public static boolean isSymmetric(TreeNode root) {//recursive, accepted
        if (root == null) return true;
        return sym(root.left, root.right);
    }
    public static boolean sym(TreeNode left, TreeNode right) {
        if (left == null) return right == null;//these two lines are concise!
        if (right == null) return left == null;
        if (left.val != right.val) return false;
        if (sym(left.left, right.right) && sym(left.right, right.left)) {
            return true;
        }
        return false;
    }
    
    public int minDepth(TreeNode root) {//solved after consulting the answer
        if (root == null) return 0;
        int ldepth = minDepth(root.left);
        int rdepth = minDepth(root.right);
        if (ldepth == 0 && rdepth == 0) {
            return 1;
        }
        if (ldepth == 0) {
            ldepth = Integer.MAX_VALUE;
        }
        if (rdepth == 0) {
            rdepth = Integer.MAX_VALUE;
        }
        return Math.min(ldepth, rdepth) + 1;
    }
}
