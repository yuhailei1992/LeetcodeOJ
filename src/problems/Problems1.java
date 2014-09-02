package problems;
import problems.Datastructures.ListNode;

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

}
