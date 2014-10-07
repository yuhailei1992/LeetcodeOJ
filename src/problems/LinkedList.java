package problems;
import problems.Datastructures.ListNode;
public class LinkedList {
    public static void reverse_n (ListNode n0, int n) {//passed
        ListNode temphead = n0;
        ListNode p_prev = temphead;
        ListNode p;
        ListNode lasttail = new ListNode(0);
        ListNode head = null;
        int m = 3;
        for (int j = 0; j < m; ++j) {
            //int n = 2;
            for (int i = 0; i < n-1 && p_prev.next != null; ++i) {
                p = p_prev.next;
                p_prev.next = p.next;
                p.next = temphead;
                temphead = p;
            }
            lasttail.next = temphead;//concatenate the previous sublist with current sublist
            lasttail = p_prev;//points to the tail of current sublist
            p_prev = p_prev.next;
            if (j == 0) head = temphead;//get the genuine head
            temphead = p_prev;
        }
        //print the list
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
    public static void testLinkedList () {
        ListNode n0 = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;
        //next, reverse!
        reverse_n(n0, 2);
    }
}
