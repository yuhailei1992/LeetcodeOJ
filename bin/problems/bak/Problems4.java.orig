package problems;
import java.util.ArrayList;
import java.util.*;

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
    	String haystack = "aaa";
    	String needle = "a";
    	System.out.println(strStr(haystack, needle));
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
    
    public static List<String> anagrams(String[] strs) {//accepted after consulting the solution
    	ArrayList<String> ret = new ArrayList<String>();
    	if (strs == null || strs.length == 0) return ret;
    	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	for (int i = 0; i < strs.length; ++i) {
    		String temp = strs[i];
    		char[] chars = temp.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (map.containsKey(sorted)) {
            	map.get(sorted).add(strs[i]);
            }
            else {
            	ArrayList<String> list = new ArrayList<String>();
            	list.add(strs[i]);
            	map.put(sorted, list);
            }
    	}
    	Iterator<ArrayList<String>> iter = map.values().iterator();
    	while (iter.hasNext()) {
    		ArrayList<String> item = (ArrayList<String>)iter.next();
    		if (item.size() > 1) {
    			ret.addAll(item);
    		}
    	}
    	return ret;
    }
    
    public static boolean isValidBST(TreeNode root) {//passed
        if (root == null) return true;
        ArrayList<Integer> A = new ArrayList<Integer>();
        traverse_inorder(root, A);
        for (int i = 0; i < A.size() - 1; ++i) {
        	if (A.get(i) >= A.get(i+1)) return false;
        }
        return true;
    }
    
    public static void traverse_inorder(TreeNode root, ArrayList<Integer> A) {//passed
    	if (root == null) return;
    	traverse_inorder(root.left, A);
    	A.add(root.val);
    	traverse_inorder(root.right, A);
    }
    
    public static boolean isValidBST2(TreeNode root) {//better solution
    	if (root == null) return true;
    	return judge(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public static boolean judge (TreeNode node, int min, int max) {
    	if (node == null) return true;
    	if (node.val <= min || node.val >= max) {
    		return false;
    	}
    	return judge(node.left, min, node.val) && judge(node.right, node.val, max);
    }
    
    public int maxArea(int[] height) {//passed after consulting solution
        int start = 0;
        int end = height.length - 1;
        int capacity = 0;
        while (start < end) {
            int temp = (end - start) * Math.min(height[start], height[end]);
            capacity = Math.max(capacity, temp);
            if (height[start] < height[end]) {
                start++;
            }
            else {
                end--;
            }
        }
        return capacity;
    }
    
    public static List<Integer> grayCode(int n) {//accepted after consulting
    	ArrayList<Integer> res = new ArrayList<Integer>();
        if (n == 0) {
        	res.add(0);
        	return res;
        }
        else {
        	ArrayList<Integer> sub = (ArrayList<Integer>)grayCode(n-1);
        	res.addAll(sub);
        	int base = 1 << (n-1);
        	for (int i = base-1; i >= 0; --i) {
        		res.add(base + sub.get(i));
        	}
        }   
        return res;
    }
        	
	public static String strStr(String haystack, String needle) {//accepted
        if (needle == null || haystack == null) return null;
        if (haystack.equals(needle)) return haystack;
        if (needle == "") {
            return haystack;
        }
        if (haystack == "") {
            return null;
        }
        int n_len = needle.length();
        int h_len = haystack.length();
        char h_arr[] = haystack.toCharArray();
        char n_arr[] = needle.toCharArray();
        for (int i = 0; i < h_len - n_len; ++i) {
            int j = 0;
        	for (; j < n_len; ++j) {
        		if (h_arr[i+j] != n_arr[j]) {
        			break;
        		}
        	}
        	if (j == n_len) {
        		return haystack.substring(i);
        	}
        }
        return null;
    }
	
	public static TreeNode sortedArrayToBST(int[] num) {//accepted after consulting
        return buildTree(num, 0, num.length-1);
    }
	
	public static TreeNode buildTree (int[] num, int start, int end) {//accepted after consulting
		if (start > end) return null;
		if (start == end) {
			TreeNode node = new TreeNode(num[start]);
			return node;
		}
		int mid = (start + end)/2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = buildTree(num, start, mid-1);
		node.right = buildTree(num, mid+1, end);
		return node;
	}
}
