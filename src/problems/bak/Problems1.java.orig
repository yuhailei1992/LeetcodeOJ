package problems;

import problems.Datastructures.ListNode;
import problems.Datastructures.TreeNode;

public class Problems1 {
	public static int removeDuplicates(int[] A) {
        return 0;
    }
	
	public static void testListNode () {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(1);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(2);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		ListNode m1 = new ListNode(1);
		ListNode m2 = new ListNode(1);
		ListNode m3 = new ListNode(2);
		ListNode m4 = new ListNode(2);

		m1.next = m2;
		m2.next = m3;
		m3.next = m4;
		ListNode t = deleteDuplicates(m1);
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}		
	}
	
	public static void testArray () {
		int A[] = {1, 1, 2};
		removeDuplicates2(A);
	}
	public static void showList (ListNode head) {
		System.out.println("Showlist:");
		ListNode p = head;
		while (p != null) {
			System.out.println(p.val);
			p = p.next;
		}
		System.out.println("Showlist end");
	}
	
	public static ListNode deleteDuplicates(ListNode head) {//passed after consulting the answer
		ListNode prev = new ListNode (0x7fffffff);
		prev.next = head;
		head = prev;//save the head
		ListNode n1 = head;
		while (n1.next != null) {
		    ListNode n2 = n1.next;
			while (n2.next != null && n2.next.val == n2.val) {
				n2 = n2.next;
			}
			if (n1.next != n2) n1.next = n2.next;
			else n1 = n1.next;//go on to the next page
		}
		return head.next;
    }
	
	public static int searchInsert(int[] A, int target) {
        int len = A.length;
        int i = 0;
        while (i < len && A[i] < target) {
        	i++;
        }
        System.out.println(i);
        return i;
    }
	
	public static ListNode detectCycle(ListNode head) {
		if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                //do something to remember this position
            }
            else {
                if (p1.next == null) return null;
                else p1 = p1.next;
                if ((p2.next == null) || (p2.next != null && p2.next.next == null)) return null;
                else p2 = p2.next.next;
            }
        }
		return null;
    }
	
	public static int removeDuplicates2(int[] A) {//passed after consulting answer
		if (A.length < 2) return A.length;
        int len = A.length;
        int i = 1;
        int j = 1;
        while (j < len) {
        	if (A[j] > A[i-1]) {
        		A[i] = A[j];
        		++i;
        		j = i;
        	}
        	else {
        		++j;
        	}
        }
        System.out.println(i);
        return i;
    }
	
	public boolean searchMatrix(int[][] matrix, int target) {//passed after consulting answer
		if (matrix.length < 1) {
			return false;
		}
        int bottom = 0;
        int top = matrix.length*matrix[0].length-1;
        while (bottom <= top) {
        	int mid = (bottom + top) / 2, 
        			midX = mid / matrix[0].length,
                    midY = mid % matrix[0].length;
        	if (matrix[midX][midY] == target) {
        		return true;
        	}
        	else if (matrix[midX][midY] > target) {
        		top = mid - 1;
        	}
        	else {
        		bottom = mid + 1;
        	}
        }
        return false;
    }
	
	public boolean isSameTree(TreeNode p, TreeNode q) {//passed without even testing on eclipse!
        if (p == null && q == null) {
            return true;
        }
        else if (p == null && q != null) {
            return false;
        }
        else if (p != null && q == null) {
            return false;
        }
        else {
            if ((p.val == q.val) && 
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right))
            return true;
            else return false;
        }
    }
	
	
}
