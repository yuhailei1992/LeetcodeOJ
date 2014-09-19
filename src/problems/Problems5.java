package problems;
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
	
	public static void test () {
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
		showList(sortList(n0));
	}
}
