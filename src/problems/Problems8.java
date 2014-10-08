package problems;
import java.util.*;

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

    /**
     * Merge K sorted lists
     * Below are three solutions to this problem
     * 1, naive method, which TLE
     * 2, recursive method, AC
     * 3, priority queue method
     * @param lists
     * @return
     */
    //naive method:
    public static ListNode mergeKListsNaive(List<ListNode> lists) {//naive solution, TLE
        if (lists == null) return null;
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int min_idx = 0;
        int min_val;
        int depleted_cnt;
        ListNode to_insert;
        ListNode t;
        while (true) {
            min_val = Integer.MAX_VALUE;
            depleted_cnt = 0;
            for (int i = 0; i < lists.size(); ++i) {
                if (lists.get(i) == null) {
                    depleted_cnt++;
                }
                else {
                    if (lists.get(i).val < min_val) {
                        min_idx = i;
                    }
                }
            }
            if (depleted_cnt == lists.size()) {
                break;
            }
            //insert
            to_insert = lists.get(min_idx);
            lists.set(min_idx, to_insert == null? null:to_insert.next);
            t = new ListNode(to_insert.val);
            tail.next = t;
            t.next = null;
            tail = t;
        }
        return dummy.next;
    }
    //recursive method:
    public static ListNode mergeKListsRecursive(List<ListNode> lists) {//AC
        if (lists == null) return null;
        else if (lists.size() == 0) return null;
        else if (lists.size() == 1) return lists.get(0);
        else {
        	return mergeKLists2(lists, 0, lists.size()-1);
        }
    }
    public static ListNode mergeKLists2(List<ListNode> lists, int start, int end) {//AC
    	if (start == end) return lists.get(start);
    	else if (start + 1 == end) return merge2Lists(lists.get(start), lists.get(end));
    	else {//
    		int mid = (start + end) / 2;
    		ListNode a = mergeKLists2(lists, start, mid);
    		ListNode b = mergeKLists2(lists, mid+1, end);
    		return merge2Lists(a, b);
    	}
    }  
    public static ListNode merge2Lists(ListNode x, ListNode y) {//AC
    	if (x == null) return y;
    	else if (y == null) return x;
    	else {//normal situations
    		ListNode dummy = new ListNode(0);
    		ListNode tail = dummy;
    		while (x != null && y != null) {
    			if (x.val < y.val) {
    				tail.next = x;
    				x = x.next;
    				tail = tail.next;
    				tail.next = null;
    			}
    			else {
    				tail.next = y;
    				y = y.next;
    				tail = tail.next;
    				tail.next = null;
    			}
    		}
    		//one of the lists is finished
    		if (x != null) {
    			tail.next = x;
    			return dummy.next;
    		}
    		else {
    			tail.next = y;
    			return dummy.next;
    		}
    	}
    }
    //priority queue method:
    public static ListNode mergeKListsPriorityQueue(List<ListNode> lists) {//AC
        if (lists.size() == 0) return null;
    	// define a comparator
    	Comparator<ListNode> BY_VAL = new Comparator<ListNode> () {
    		public int compare(ListNode s1, ListNode s2) {
    			return s1.val - s2.val;
    		}
    	};
    	// create a queue and add all the nodes
    	PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(), BY_VAL);
    	for (int i = 0; i < lists.size(); ++i) {
    		if (lists.get(i) != null) pq.add(lists.get(i));
    	}
    	//merge
    	ListNode dummy = new ListNode(0);
    	ListNode tail = dummy;
    	while (pq.isEmpty() == false) {
    		ListNode temp = pq.poll();
    		tail.next = temp;
    		tail = tail.next;
    		if (temp.next != null) pq.add(temp.next);
    	}
    	//return head
    	return dummy.next;
    }
    /**
     * The overall run time complexity should be O(log(n))
     * So we use binary search.
     * @param A
     * @param B
     * @return
     */
    public static double findMedianSortedArrays(int A[], int B[]) {
        return 0.0;
    }
    
    public static int sqrt(int x) {
        if (x < 0) return -1;
        else if (x < 2) return x;

        long xt = (long)x;
        int low = 1;
        int high = x/2+1;
        int mid = 0;
        while (high >= low) {
            mid = low + (high - low)/2;
            long midt = (long)mid * (long)mid;
            if (midt < xt) low = mid+1;
            else high = mid-1;
            System.out.println(mid);
        }
        return mid;
    }

    public static int[] searchRange(int[] A, int target) {//AC
    	int low = binSearchLess(A, target, 0, A.length-1);
    	int high = binSearchLess(A, target+1, 0, A.length-1)-1;
    	int ret[] = {-1, -1};
    	if (low > A.length-1 || A[low] != target) return ret;
    	if (low > high) high = low;
    	if (A[high] != target) high--;
    	ret[0] = low;
    	ret[1] = high;
    	System.out.println("low" + low + '\n' + "high" + high);
    	return ret;
    }
    public static int binSearchLess(int[] A, int target, int start, int end) {//AC
    	if (start == end) return start;
    	else {
    		int mid;
    		while (start < end) {
    			mid = (start + end) / 2;
        		if (A[mid] < target) {
        			start = mid+1;
        		}
        		else {
        			end = mid-1;
        		}
    		}
    		if (A[start] == target) return start;
    		else return start+1;
    	}
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {//TLE
        if (root == null) return null;
        List<List<Integer>> lli = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        List<TreeNode> ltn0 = new ArrayList<TreeNode>();
        List<TreeNode> ltn1 = new ArrayList<TreeNode>();
        List<TreeNode> ltn_curr = ltn0;
        List<TreeNode> ltn_child = ltn1;
        ltn_curr.add(root);
        while (ltn_curr.isEmpty() == false) {
        	ltn_child.clear();
        	for (TreeNode tn : ltn_curr) {
            	temp.add(tn.val);
            	if (tn.left != null) ltn_child.add(tn.left);
            	if (tn.right != null) ltn_child.add(tn.right);
            }
        	lli.add(temp);
        	if (ltn_curr == ltn0) {
        		ltn_curr = ltn1;
        		ltn_child = ltn0;
        		ltn_child.clear();
        	}
        	else if (ltn_curr == ltn1) {
        		ltn_curr = ltn0;
        		ltn_child = ltn1;
        		ltn_child.clear();
        	}
        }
        return lli;
    }
    
    public static List<List<Integer>> levelOrder2(TreeNode root) {//AC
    	List<List<Integer>> lli = new ArrayList<List<Integer>>();//return val
    	if (root == null) return lli;
        Queue<TreeNode> qtn = new java.util.LinkedList<TreeNode>();
        qtn.add(root);
        while (qtn.isEmpty() == false) {
        	ArrayList<TreeNode> ltn = new ArrayList<TreeNode>();
        	ArrayList<Integer> li = new ArrayList<Integer>();
        	while (qtn.isEmpty() == false) {
        		TreeNode temp = qtn.remove();
        		li.add(temp.val);
        		ltn.add(temp);
        		System.out.println(temp.val);
        	}
        	lli.add(li);
        	for (TreeNode iter : ltn) {
        		if (iter.left != null) qtn.add(iter.left);
        		if (iter.right != null) qtn.add(iter.right);
        	}
        	
        }
        return lli;
    }
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {//AC
    	List<List<Integer>> lli = new ArrayList<List<Integer>>();
    	if (root == null) return lli;
        Queue<TreeNode> qtn = new java.util.LinkedList<TreeNode>();
        qtn.add(root);
        while (qtn.isEmpty() == false) {
        	ArrayList<TreeNode> ltn = new ArrayList<TreeNode>();
        	ArrayList<Integer> li = new ArrayList<Integer>();
        	while (qtn.isEmpty() == false) {
        		TreeNode temp = qtn.remove();
        		li.add(temp.val);
        		ltn.add(temp);
        	}
        	lli.add(li);
        	for (TreeNode iter : ltn) {
        		if (iter.left != null) qtn.add(iter.left);
        		if (iter.right != null) qtn.add(iter.right);
        	}
        	
        }
        //then, reverse the arraylist
        List<List<Integer>> llirev = new ArrayList<List<Integer>>();//return val
        for (int i = lli.size()-1; i >= 0; --i) {
        	llirev.add(lli.get(i));
        }
        return llirev;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {//AC
    	List<List<Integer>> lli = new ArrayList<List<Integer>>();//return val
    	if (root == null) return lli;
        Queue<TreeNode> qtn = new java.util.LinkedList<TreeNode>();
        int depth = 0;
        qtn.add(root);
        while (qtn.isEmpty() == false) {
        	ArrayList<TreeNode> ltn = new ArrayList<TreeNode>();
        	ArrayList<Integer> li = new ArrayList<Integer>();
        	while (qtn.isEmpty() == false) {
        		TreeNode temp = qtn.remove();
        		li.add(temp.val);
        		ltn.add(temp);
        	}
        	//insert this level to return list
        	if (depth % 2 == 1) {//odd level
        		ArrayList<Integer> lirev = new ArrayList<Integer>();
        		for (int i = li.size()-1; i >= 0; --i) {
        			lirev.add(li.get(i));
        		}
        		lli.add(lirev);
        	}
        	else {
        		lli.add(li);
        	}
        	for (TreeNode iter : ltn) {
        		if (iter.left != null) qtn.add(iter.left);
        		if (iter.right != null) qtn.add(iter.right);
        	}
        	depth++;
        }
        return lli;
    }
    
    /**
     * Why did I use an array to store the maximum value?
     * Java passes parameters by value, not by pointer.
     * So, if you use an integer to remember the maximum value, you'll not be 
     * able to update the original maximum value, cause the update is made on
     * maximum value's duplicate
     * @param root
     * @return
     */
    public static int maxPathSum(TreeNode root) {//AC after consulting
        if (root == null) return 0;
        int max[] = new int[]{Integer.MIN_VALUE};
        maxPath(root, max);
        return max[0];
    }
    
    private static int maxPath(TreeNode root, int[] max) {//AC
    	if (root == null) return 0;
    	int left = maxPath(root.left, max);
    	int right = maxPath(root.right, max);
    	int arch = left + right + root.val;//what if left or right is negative?
    	int ret = Math.max(root.val, Math.max(left, right) + root.val);
    	max[0] = Math.max(max[0], Math.max(arch, ret));
    	return ret;
    }

    public static List<TreeNode> generateTrees(int n) {//AC after consulting
        return dfs(0, n-1);
    }  
    private static ArrayList<TreeNode> dfs (int start, int end) {
    	ArrayList<TreeNode> ret = new ArrayList<TreeNode>();
    	if (start > end) {
    		ret.add(null);//don't understand
    		return ret;
    	}
    	else {
    		for (int i = start; i <= end; ++i) {
    			ArrayList<TreeNode> left = dfs(start, i-1);
    			ArrayList<TreeNode> right = dfs(i+1, end);
    			
    			for (int m = 0; m < left.size(); ++m) {
    				for (int n = 0; n < right.size(); ++n) {
    					TreeNode root = new TreeNode(i+1);//use the ith elem as root
    					root.left = left.get(m);
    					root.right = right.get(n);
    					ret.add(root);
    				}
    			}
    		}
    		return ret;
    	}
    }
    
    public static void reorderList(ListNode head) {//AC, bug-free
    	
    	if (head == null || head.next == null) return;
    	ListNode fast = head;
    	ListNode slow = head;
    	while (fast.next != null && fast.next.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	ListNode tail = slow;
    	slow = slow.next;
    	tail.next = null;
    	//then, reverse the second half
    	ListNode temp = reverseList(slow);
    	//then, merge
    	head = interleaveLists(head, temp);
    	
    }
    
    private static ListNode reverseList (ListNode head) {
    	ListNode curr = head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = curr;
    	
    	while (curr.next != null) {
    		ListNode to_insert = curr.next;
    		curr.next = curr.next.next;
    		to_insert.next = dummy.next;
    		dummy.next = to_insert;
    	}
    	
    	return dummy.next;
    }
    
    private static ListNode interleaveLists (ListNode x, ListNode y) {
    	ListNode dummy = new ListNode(0);
    	ListNode tail = dummy;
    	while (x != null && y != null) {
    		tail.next = x;
    		tail = x;
    		x = x.next;
    		tail.next = y;
    		tail = y;
    		y = y.next;
    	}
    	if (x == null) {
    		tail.next = y;
    	}
    	else {
    		tail.next = x;
    	}
    	return dummy.next;
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
    	//zigzagLevelOrder(n0);
    	ListNode l0 = new ListNode(0);
    	ListNode l1 = new ListNode(1);
    	ListNode l2 = new ListNode(2);
    	ListNode l3 = new ListNode(3);
    	ListNode m0 = new ListNode(9);
    	ListNode m1 = new ListNode(8);
    	ListNode m2 = new ListNode(7);
    	m0.next = m1;
    	m1.next = m2;
    	//ListNode l4 = new ListNode(4);
    	l0.next = l1;
    	l1.next = l2;
    	l2.next = l3;
    	//l3.next = l4;
    	//reorderList(l0);
    	for (ListNode temp = interleaveLists(l0, m0); temp != null; temp = temp.next) {
    		System.out.println(temp.val);
    	}
    }
}
