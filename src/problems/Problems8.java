package problems;
import java.util.*;

import ScoreList.ScoreListEntry;
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
    	ListNode n0 = new ListNode(0);
    	ListNode n1 = new ListNode(2);
    	ListNode n2 = new ListNode(4);
    	ListNode n3 = new ListNode(1);
    	ListNode n4 = new ListNode(3);
    	n0.next = n1;
    	n1.next = n2;
    	n3.next = n4;
    	ListNode temp = merge2Lists(n0, n3);
    	while (temp != null) {
    		System.out.println(temp.val);
    		temp = temp.next;
    	}
    	List<ListNode> ln = new ArrayList<ListNode>();
    }
}