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
		m1.next = m2;
		ListNode t = deleteDuplicates2(m1);
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
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
	
	public static void testMerge () {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(6);
		ListNode n4 = new ListNode(7);
		ListNode n5 = new ListNode(9);
		ListNode n6 = new ListNode(24);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		ListNode m1 = new ListNode(4);
		ListNode m2 = new ListNode(6);
		ListNode m3 = new ListNode(7);
		ListNode m4 = new ListNode(9);
		ListNode m5 = new ListNode(10);
		ListNode m6 = new ListNode(23);
		m1.next = m2;
		m2.next = m3;
		m3.next = m4;
		m4.next = m5;
		m5.next = m6;
		
	}
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {//pass
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
		//printList
		ListNode p = addihead.next;
		while (p != null) {
			System.out.println(p.val);
			p = p.next;
		}
        return l1;
    }
	
	public static void testMergeArray () {
		int A[] = {2, 3, 5, 6, 7, 0, 0, 0, 0};
		int B[] = {4, 6, 8, 9};
		merge(A, 5, B, 4);
		for (int i = 0; i < 9; ++i) {
			System.out.println(A[i]);
		}
	}
	public static void merge(int A[], int m, int B[], int n) {//pass
		int pm = m - 1;
		int pn = n - 1;
		int i = m + n -1;
		while (i >= 0 && pm >= 0 && pn >= 0) {
        	if (A[pm] > B[pn]) {
        		A[i] = A[pm];
        		pm--;
        	}
        	else {
        		A[i] = B[pn];
        		pn--;
        	}
        	i--;
        }
		while (pn >= 0) {
			A[pn] = B[pn];
			pn--;
		}
    }
	
	public static boolean isPalindrome(int x) {//passed but not accepted
		//because this solution is not in-place
        String str = Integer.toString(x);
        char[] strarr = str.toCharArray();
        System.out.println(strarr[0]);
        int len = strarr.length;
        for (int j = 0; j < len; ++j) {
        	System.out.println(strarr[j]);
        }
        for (int i = 0; i < len/2; ++i) {
        	if (strarr[i] != strarr[len - i - 1]) {
        		System.out.println("Not palin");
        		return false;
        	}
        }
		System.out.println("palin");
        return true;
    }
	
	public static boolean isPalindrome2(int x) {//pass
		//negative numbers are not palindrome
		if (x < 0) {
			return false;
		}
		
		long x_bak = x;
		long x_new = 0;
		while (x != 0) {
			x_new = x_new * 10;
			x_new += x % 10;
			x = x / 10;
		}

		System.out.println(x_new);
		if (x_bak == x_new) return true;
		else return false;
	}
	
	public static ListNode insertionSortList(ListNode head) {//passed but exceeded time limit
		ListNode p1 = head.next;
		ListNode prev = head;
		
		ListNode new_head = new ListNode(0x80000000);
		ListNode temp = new ListNode(head.val);
		new_head.next = temp;
		while (p1 != null) {
			prev.next = p1.next;
			ListNode p2_prev = new_head;
			ListNode p2 = new_head.next;
			for (; p2 != null; p2 = p2.next, p2_prev = p2_prev.next) {
				System.out.println("Oh" + p1.val);
				if (p1.val < p2.val) {
					p2_prev.next = p1;
					p1.next = p2;
					break;
				}
			}
			if (p2 == null) {//p2 is at the tail. Insert after
				p2_prev.next = p1;
			}
			//now, decide whether to continue or not
			if (prev.next != null) {
				p1 = prev.next;
			}
			else {//no more node
				break;
			}
		}
		ListNode t = new_head.next;
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}
		return new_head.next;
    }
	
	public static ListNode insertionSortList2(ListNode head) {//accepted after consulting answer
		if (head == null || head.next == null) {
			return head;
		}
		else {
			ListNode dummy = new ListNode(0);
			ListNode p = dummy;
			while (head != null) {
				ListNode toinsert = head;
				head = head.next;
				
				if (toinsert.val < p.val) {
					p = dummy;//go back to the head;
				}				
				while (p.next != null && toinsert.val > p.next.val) {
					p = p.next;
				}
				toinsert.next = p.next;
				p.next = toinsert;
			}
			ListNode t = dummy.next;
			while (t != null) {
				System.out.println(t.val);
				t = t.next;
			}
			return dummy.next;
		}
 	}
	
	public class Table {
	       protected java.util.HashMap<String, Integer> map = 
	             new java.util.HashMap<String, Integer>();

	       public Integer get(String key) { return map.get(key); }

	       public Integer put(String key, Integer value) {
	          if (map.containsKey(key)) {

	           return value;
	          } else {
	            return map.put(key, value);
	          }
	       }
	       // other methods goes here
	    }
	
	public static ListNode deleteDuplicates(ListNode head) {//exceeded time limit
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode p = head;
		
		java.util.HashMap<Integer, Integer> map1 = new java.util.HashMap<Integer, Integer>();
		
		while (p != null) {
			if (map1.containsKey(p.val)) {
				if (p.next != null) {
					prev.next = p.next;
					p = prev.next;
				}
				else {
					prev.next = p.next;
				}
			}
			else {
				map1.put(p.val, 0);
				if (p.next != null) {
					prev = prev.next;
					p = prev.next;
				}
				else {
					break;
				}
			}
		}
		ListNode t = dummy.next;
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}
        return null;
    }
	
	public static ListNode deleteDuplicates2(ListNode head) {//pass
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p = head;
		while (p != null && p.next != null) {
			while (p.next != null && p.next.val == p.val) {
				p.next = p.next.next;
			}
			p = p.next;
		}
		return head;
	}

}
