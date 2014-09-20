package problems;
import java.util.*;
import java.util.Arrays;
import problems.Datastructures.*;


public class Problems5 {
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode addihead = new ListNode(0);
		ListNode p1 = l1;
		ListNode p2 = l2;
		ListNode p3 = addihead;
		while (p1 != null && p2 != null) {
			if (p1.val < p2.val) {
				ListNode temp = new ListNode(p1.val);
				p3.next = temp;
				p3 = temp;
				p1 = p1.next;
			}
			else {
				ListNode temp = new ListNode(p2.val);
				p3.next = temp;
				p3 = temp;
				p2 = p2.next;
			}
		}
		while (p1 != null && p2 == null) {
			ListNode temp = new ListNode(p1.val);
			p3.next = temp;
			p3 = temp;
			p1 = p1.next;
		}
		while (p2 != null && p1 == null) {
			ListNode temp = new ListNode(p2.val);
			p3.next = temp;
			p3 = temp;
			p2 = p2.next;
		}
        return addihead.next;
    }
	
	public static ListNode sortList(ListNode head) {//passed after consulting
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode s1 = head;
        ListNode s2 = head;
        while (s2.next != null && s2.next.next != null) {
        	s1 = s1.next;
        	s2 = s2.next.next;
        }
        ListNode head1 = head;
        ListNode head2 = s1.next;
        s1.next = null;
        head1 = sortList(head1);
        head2 = sortList(head2);
        return mergeTwoLists(head1, head2);
    }
	
	public static void showList (ListNode head) {
		if (head == null) {
			System.out.println("null list");
			return;
		}
		ListNode p = head;
		while (p != null) {
			System.out.print(p.val + "->");
			p = p.next;
		}
		System.out.println();
	}

	/* swapPairs test case:
	    ListNode n0 = new ListNode(3);
		ListNode n1 = new ListNode(32);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(4);
		ListNode n4 = new ListNode(6);
		ListNode n5 = new ListNode(3);
		ListNode n6 = new ListNode(2);
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;
		ListNode m0 = new ListNode(4);
		ListNode m1 = new ListNode(5);
		m0.next = m1;//bug exist
		showList(swapPairs(m0));
	 */
	public static ListNode swapPairs(ListNode head) {//passed
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode addi_head = new ListNode(0);
        addi_head.next = head;
        ListNode p_prev = addi_head;
        ListNode p = head;
        while (p != null && p.next != null) {
        	ListNode p_next = p.next;
        	p.next = p_next.next;
        	p_prev.next = p_next;
        	p_next.next = p;
        	p_prev = p_prev.next.next;
        	p = p.next;
        }
        return addi_head.next;
    }
	
	public static List<List<Integer>> generate(int numRows) {//accepted
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (numRows == 0) {
        	return ret;
        }
        for (int i = 0; i < numRows; ++i) {
        	ArrayList<Integer> temp = new ArrayList<Integer>();
        	if (i == 0) {
        		temp.add(1);
        	}
        	else if (i == 1) {
        		temp.add(1);
        		temp.add(1);
        	}
        	else {
        		List<Integer> pre = ret.get(i-1);
        		temp.add(1);
        		for (int j = 0; j < pre.size()-1; ++j) {
        			temp.add(pre.get(j) + pre.get(j+1));
        		}
        		temp.add(1);
        	}
        	ret.add(temp);
        }
        return ret;
    }
	
	public static void sortColors(int[] A) {//accepted
        if (A == null || A.length < 2) return;
        int pleft = 0, pright = A.length-1;
        for (int p = 0; p <= pright; p++) {
        	if (A[p] == 0) {
        		int temp = A[p];
        		A[p] = A[pleft];
        		A[pleft] = temp;
        		pleft++;
        	}
        	else if (A[p] == 2) {
        		int temp = A[p];
        		A[p] = A[pright];
        		A[pright] = temp;
        		pright--;
        		p--;
        	}
        }
        showArray(A);
    }

	public static int uniquePaths(int m, int n) {//accepted
		int[][] map = new int[m][n];
		for (int i = 0; i < m; ++i) {
			map[i][0] = 1;
		}
		for (int j = 0; j < n; ++j) {
			map[0][j] = 1;
		}
		for (int i = 1; i < m; ++i) {
			for (int j = 1; j < n; ++j) {
				map[i][j] = map[i-1][j]+map[i][j-1];
			}
		}
		return map[m-1][n-1];
    }
	
	/*public static List<String> generateParenthesis(int n) {
		ArrayList<String> ret = new ArrayList<String>();
        if (n == 0) return ret;
        if (n == 1) {
        	ret.add("()");
        	return ret;
        }
        else {
        	//individual ones
        	String temp1 = "";
        	for (int i = 0; i < n; ++i) {
        		temp1 += "()";
        	}
        	ret.add(temp1);
        	//aside ones;
        	Iterator<String> iter = ret.iterator();
        	String temp2 = "";
        	temp2 += "()";
        	//temp2 += 
        }
        
    }*/
	
	int sum = 0;
    public int sumNumbers(TreeNode root) {//AC
		int base = 0;
		subRoutine(root, base);
		return sum;
	}
	
	public void subRoutine(TreeNode root, int base) {
		if (root == null) {
			return;
		}
		base = base*10 + root.val;
		if (root.left == null && root.right == null) {
			sum += base;
		}
		subRoutine(root.left, base);
		subRoutine(root.right, base);
	}

	public static List<Integer> getRow(int rowIndex) {
        return null;
    }

	public static void showArray (int[] n) {
		for (int i = 0; i < n.length; ++i) {
			System.out.print(n[i]);
		}
		System.out.println("end");
	}
	
	
	public static void test () {
		TreeNode n0 = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		n0.left = n1;
		n0.right = n2;
		Problems5 testobj = new Problems5();
		testobj.sumNumbers2(n0);
	}

}
