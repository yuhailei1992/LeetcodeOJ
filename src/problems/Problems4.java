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
    
    /*public static TreeNode sortedArrayToBST(int[] num) {
        int len = num.length;
        if (len == 0) return null;
        int mid = len / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = new TreeNode(num[mid/2]);
        root.right = new TreeNode(num[mid + mid/2]);
        
    }*/
    public static void test () {
    	String[] strs = {"hello", "hell", "hellodarkness", "hel"};
    	int [] A = {1, 1, 1, 1, 3, 3};
    	removeDuplicates(A);
    	//longestCommonPrefix(strs);
    	for (int i = 0; i < removeDuplicates(A); ++i) {
    		System.out.println(A[i]);
    	}
    	
    }
    public static String longestCommonPrefix(String[] strs) {//accepted
    	if (strs == null) return null;
        if (strs.length == 0) return "";
        char prefix[] = strs[0].toCharArray();
        int len = strs[0].length();
        for (int i = 1; i < strs.length; ++i) {
        	len = Math.min(len,  strs[i].length());
        	for (int j = 0; j < len; ++j) {
        		if (strs[i].charAt(j) != prefix[j]) {
        			if (j < len) {
        				len = j;
        			}
        			break;
        		}
        	}
        }
        return new String(prefix, 0, len);
    }
    
    public static int removeDuplicates(int[] A) {//copied from the answer
        if (A.length <= 2) return A.length;
        int len = A.length;
        int cnt = 0;
        int j = 0;
        for (int i = 1; i < len; ++i) {
        	if (A[i] == A[j]) {
        		if (cnt == 0) {
        			A[++j] = A[i];
        		}
        		++cnt;
        	}
        	else {
        		A[++j] = A[i];
        		cnt = 0;
        	}
        }
        return j+1;
    }
}
