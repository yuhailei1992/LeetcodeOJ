package problems;

import java.util.Stack;

import problems.Datastructures.TreeNode;
import problems.Datastructures.ListNode;

public class Problems {
	public static void reverseWords () {//pass
		String s = "    the sky is blue";
        s = s.trim();//remove leading and trailing spaces
        System.out.println(s);
        String buf = "";//string buffer
        String[] strsplit = s.split("\\s+");
        for (int i = strsplit.length - 1; i >= 0; --i) {
        	buf += strsplit[i];
        	if (i != 0) {
        		buf += " ";
        	}
        	System.out.println(strsplit[i]);
        	System.out.println(strsplit[i].length());
        }
        System.out.println(buf);
    }
	
	public static void evalRPN () {//pass
		String[] tokens = {"3", "-4", "+"};
		int len = tokens.length;
		String[] stk = new String[len];
		String ops = "+-*/";		
		int stkptr = 0;
		for (int i = 0; i < len; ++i) {
			if (!ops.contains(tokens[i])) {
				stk[stkptr] = tokens[i];
				stkptr++;
			}
			else {
				int temp = 0;
				int index = ops.indexOf(tokens[i]);
				switch (index) {
					case 0: temp = Integer.parseInt(stk[stkptr - 2]) + Integer.parseInt(stk[stkptr - 1]);
						break;
					case 1: temp = Integer.parseInt(stk[stkptr - 2]) - Integer.parseInt(stk[stkptr - 1]);
						break;
					case 2: temp = Integer.parseInt(stk[stkptr - 2]) * Integer.parseInt(stk[stkptr - 1]);
						break;
					case 3: temp = Integer.parseInt(stk[stkptr - 2]) / Integer.parseInt(stk[stkptr - 1]);
						break;
					default :
						break;
				}
				stk[stkptr - 2] = Integer.toString(temp);
				stkptr--;
			}
		}
		System.out.println(stk[0]);
	}
	
	public static void P3 () {
		
	}
	
	public static void reverseInteger () {//pass
		int x = -321;
		int isNeg = 0;
		int x_new = 0;
		if (x < 0) {
			x = -x;
			isNeg = 1;
		}
		while (x != 0) {
			int m = x % 10;
			x_new = x_new * 10;
			x_new += m;
			x = x / 10;
		}
		if (isNeg == 1) {
			x_new = -x_new;
		}
		System.out.println(x_new);
	}
	
	public static double pow (double x, int n) {//pass
		if (n == 0) return 1;
		else if (n == 1) return x;
		else if (n == -1) return 1/x;
		else {
			double temp = pow(x, Math.abs(n/2));
			if (n < 0) {
				if (n % 2 == 0) return 1 / (temp * temp);
				else return 1 / (x * temp * temp);
			}
			else {
				if (n % 2 == 0) return temp * temp;
				else return x * temp * temp;
			}
		}
	}
	//addTwoNumbers accepted
	public static void testListNode () {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(2);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		ListNode p = n1;
		partition(n1, 3);
		while (p != null) {
			System.out.println(p.val);
			p = p.next;
		}
	}
	
	public static ListNode partition (ListNode head, int x) {//pass
		if (head == null) return null;
		ListNode addiHead1 = new ListNode(0);
		addiHead1.next = head;
		ListNode addiHead2 = new ListNode(0);
		ListNode p1 = head;
		ListNode prev = addiHead1;
		ListNode p2 = addiHead2;
		while (p1 != null) {
			if (p1.val < x) {
				p1 = p1.next;
				prev = prev.next;
			}
			else {
				prev.next = p1.next;
				p2.next = p1;
				p1 = prev.next;
				p2 = p2.next;
			}
		}
		p2.next = null;
		prev.next = addiHead2.next;
		
        return null;
    }
	
	public static int singleNumber() {
        int[] A = {2, 2, 3, 3, 4, 4, 5, 5, 7};
        int len = A.length;
        int temp = 0;
        for (int i = 0; i < len; ++i) {
        	temp = temp ^ A[i];
        }
        return temp;
    }
	
	public static void testTreeNode () {
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(4);
		TreeNode n3 = new TreeNode(5);
		TreeNode n4 = new TreeNode(2);
		TreeNode n5 = new TreeNode(12);
		TreeNode n6 = new TreeNode(33);
		TreeNode n7 = new TreeNode(44);
		TreeNode n8 = new TreeNode(55);
		TreeNode n9 = new TreeNode(78);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n4.left = n6;
		n6.right = n7;
		n6.left = n8;
		n8.left = n9;
		System.out.println("maxdepth is " + maxDepth(n1));
	}
	
	public static void printLevelOrder(TreeNode root, int depth) {//PASS
		if (root == null) {
			System.out.println ("NULL TREE");
		}
		else {
			for (int i=0; i < depth; ++i){
				System.out.println ( printLevel(root, i) );
			}
		}
	}
	
	public static String printLevel(TreeNode node, int depth) {//PASS
		if (node == null) {
			return "";
		}
		else if(depth == 0) {
			String str = ""+node.val;
			return str;
		}
		else if (depth > 0){
			String leftStr = printLevel(node.left, depth-1);//the depth decres
			String rightStr = printLevel(node.right, depth-1);
			return leftStr+"_"+rightStr;
		}
		else return "";//default case
	}
	
	public static int maxDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}		
		else {
			System.out.println(node.val);
			int ldepth = maxDepth(node.left);
			int rdepth = maxDepth(node.right);
			if (ldepth > rdepth) return 1 + ldepth;
			else return 1 + rdepth;
		}
    }
	
	public int singleNumber(int[] A) {//pass
        int len = A.length;
        int temp = 0;
        for (int i = 0; i < len; ++i) {
        	temp = temp ^ A[i];
        }
        return temp;
    }
	
	public int singleNumber2(int[] A) {//solved after getting hints
        int ones = 0;
        int twos = 0;
        int xthrees = 0;
        int len = A.length;
        for (int i = 0; i < len; ++i) {
            twos = twos | (ones & A[i]);
            ones = ones ^ A[i];
            xthrees = ~(ones & twos);
            ones = xthrees & ones;
            twos = xthrees & twos;
        }
        return ones;
    }
	
	public boolean hasCycle(ListNode head) {//pass
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return true;
            }
            else {
                if (p1.next == null) return false;
                else p1 = p1.next;
                if ((p2.next == null) || (p2.next != null && p2.next.next == null)) return false;
                else p2 = p2.next.next;
            }
        }
        return false;
    }
}
