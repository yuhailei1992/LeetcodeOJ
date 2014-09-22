package problems;
import java.util.Arrays;

import problems.Datastructures.*;
import problems.Helpers.*;
public class Problems7 {
	/**Test case
	    ListNode n0 = new ListNode(1);
		ListNode n1 = new ListNode(2);
		ListNode n2 = new ListNode(3);
		ListNode n3 = new ListNode(4);
		ListNode n4 = new ListNode(5);
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		reverseKGroup(n0, 3);
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {//AC
        if (head == null || k == 0) return head;//corner case
        //count the length of list. If len < k, return;
        ListNode counter = head;
        int len = 0;
        while (counter != null) {
            len++;
            counter = counter.next;
        }
        if (len < k) return head;
        //do the regular reverses
        ListNode temphead = head;
		ListNode p_prev = temphead;
		ListNode p;
		ListNode lasttail = new ListNode(0);
		ListNode newhead = null;
		int m = len / k;//number of reverses
		for (int j = 0; j < m && p_prev != null; ++j) {
			for (int i = 0; i < k-1 && p_prev.next != null; ++i) {
				p = p_prev.next;
				p_prev.next = p.next;
				p.next = temphead;
				temphead = p;
			}
			lasttail.next = temphead;//concatenate the previous sublist with current sublist
			lasttail = p_prev;//points to the tail of current sublist
			if (p_prev != null) p_prev = p_prev.next;
			if (j == 0) newhead = temphead;//get the genuine head
			temphead = p_prev;
		}
		
		//print the list
		ListNode temp = newhead;
		while (temp != null) {
			System.out.println(temp.val);
			temp = temp.next;
		}
		
		return newhead;
    }
	
	public static String multiply(String num1, String num2) {//AC
        if (num1 == null || num2 == null) return null;
        if (num1.equals("0") || num2.equals("0")) return "0";
        int carry = 0;
        int len1 = num1.length();
        int len2 = num2.length();
        int result[] = new int[len1 + len2];
        for (int j = len2-1; j >=0; --j) {//nested loop. Calculate the corresponding digits.
        	int tempb = num2.charAt(j) - '0';
        	for (int i = len1-1; i >= 0; --i) {
	        	int tempa = num1.charAt(i) - '0';
	        	int temp = tempa * tempb + carry + result[i+j+1];
	        	result[i+j+1] = temp % 10;
	        	carry = temp / 10;
        	}
        	if (carry != 0) {//remaining carry
        		result[j] = carry;
        		carry = 0;
        	}
        }
        int i = 0;
        while (i < result.length) {//search for the valid start of string
        	if (result[i] != 0) break;
        	++i;
        }
        String ret = "";
        for (; i < result.length; ++i) {//convert integers to string
        	ret += result[i];
        }
        return ret;
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

	/**
	 * Use regex to parse the string into an array of string
	 * then return the length of the last element. 
	 * @param s
	 * @return
	 */
	public static int lengthOfLastWord(String s) {//AC
        if (s == null) return 0;
        String[] splited = s.split("\\s+");
        if (splited.length == 0) return 0;
        return splited[splited.length-1].length();
    }
	
	public static boolean isPalindrome(String s) {//TLE
        if (s == null || s.length() == 0) return true;
        String s1 = "";
        for (int i = 0; i < s.length(); ++i) {
        	char temp = s.charAt(i);
        	if ((temp >= '0' && temp <= '9') || (temp >= 'a' && temp <= 'z')) {
        		s1 += temp;
        	}
        	else if (temp >= 'A' && temp <= 'Z') {
        		temp += 32;
        		s1 += temp;
        	}
        }
        String reverse = new StringBuffer(s1).reverse().toString();
        if (s1.equalsIgnoreCase(reverse)) return true;
        else return false;
    }
	
	/**
	 * Jump over the non alphanumeric characters;
	 * Conform upper case and lower case characters;
	 * then compare
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome2(String s) {//AC
		if (s == null || s.length() < 2) return true;
		int i = 0, j = s.length()-1;
		while (i <= j) {
			char tempi = s.charAt(i);
			char tempj = s.charAt(j);
			if (((tempi >= 'a' && tempi <= 'z') 
					|| (tempi >= 'A' && tempi <= 'Z')
					|| (tempi >= '0' && tempi <= '9')) == false) {
				i++;
				continue;
			}
			if (((tempj >= 'a' && tempj <= 'z') 
					|| (tempj >= 'A' && tempj <= 'Z')
					|| (tempj >= '0' && tempj <= '9')) == false) {
				j--;
				continue;
			}
			if (i <= j) {
				if (tempi >= 'A' && tempi <= 'Z') tempi += 32;
				if (tempj >= 'A' && tempj <= 'Z') tempj += 32;
				if (tempi != tempj) return false;
			}
			++i;
			--j;
		}
		return true;
	}
	
	public static void test () {
		String s = "aA";
		if (isPalindrome2(s) == true) {
			System.out.println("yeah");
		}
	}
}
