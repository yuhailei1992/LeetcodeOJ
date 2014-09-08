package problems;
import problems.Datastructures.TreeNode;

public class Problems4 {
	public static boolean isSymmetric(TreeNode root) {//recursive, accepted
        if (root == null) return true;
        return sym(root.left, root.right);
    }
    public static boolean sym(TreeNode left, TreeNode right) {
        if (left == null) return right == null;
        if (right == null) return left == null;
        if (left.val != right.val) return false;
        if (sym(left.left, right.right) && sym(left.right, right.left)) {
            return true;
        }
        return false;
    }
}
