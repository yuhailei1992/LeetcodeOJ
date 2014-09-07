package problems;
import problems.Datastructures.ListNode;
public class Problems3 {
	public static int romanToInt(String s) {//accepted
        if (s == null) {
            return 0;
        }
        int len = s.length();
        int val = 0;
        for (int i = 0; i < len; ++i) {
        	val += c2n(s.charAt(i));
        	int t = i;
        	while (t > 0 && c2n(s.charAt(t)) > c2n(s.charAt(t-1))) {
        		val -= 2*c2n(s.charAt(t-1));
        		--t;
        	}
        }
        System.out.println(val);
        return val;
    }
    public static int c2n (char s) {
    	switch (s) {
    		case 'I' :return 1;
    		case 'V' :return 5;
    		case 'X' :return 10;
    		case 'L' :return 50;
    		case 'C' :return 100;
    		case 'D' :return 500;
    		case 'M' :return 1000;
    		default :return 0;
    	}
    }
    
    public static String intToRoman(int num) {//accepted
        if (num == 0) {
        	return "";
        }
        String buf = "";
        char roman[][] = {{'I', 'V'}, {'X', 'L'}, {'C', 'D'}, {'M', 'N'}};
        int scale = 1000;
        int i = 3;
        while (num != 0 && i >= 0) {
        	int temp = num / scale;
        	if (temp != 0) {
	        	if (temp <= 3) {
	        		for (int j = 0; j < temp; ++j) {
	        			buf += roman[i][0];
	        		}
	        	}
	        	else if (temp < 5) {
	        		buf += roman[i][0];
	        		buf += roman[i][1];
	        	}
	        	else if (temp <= 8) {
	        		buf += roman[i][1];
	        		for (int j = 5; j < temp; ++j) {
	        			buf += roman[i][0];
	        		}
	        	}
	        	else {
	        		buf += roman[i][0];
	        		buf += roman[i + 1][0];
	        	}
        	}
        	num %= scale;
        	scale /= 10;
        	--i;
        }
        System.out.println(buf);
        return buf;
    }
    
    public static int[] plusOne(int[] digits) {//this solution is good, but overflows.
        if (digits == null) return digits;
        long num = 0;
        for (int i = 0; i < digits.length; ++i) {
            num *= 10;
            num += digits[i];
        }
        num++;
        //decide if there is a carry
        long temp = num;
        for (int j = 0; j < digits.length; ++j) {
            temp /= 10;
        }
        //
        int newlen = digits.length;
        if (temp != 0) {
            newlen++;
        }
        int ret[] = new int[newlen];
        for (int j = newlen - 1; j >=0; --j) {
            ret[j] = (int)(num % 10);
            num /= 10;
        }
        for (int j = 0; j < ret.length; ++j) {
        	System.out.print(ret[j]);
        }
        return ret;
    }
    
    public static int[] plusOne1(int[] digits) {//acceptedg
    	if (digits == null) return digits;
    	int len = digits.length;
    	int carry = 1;
    	for (int i = len - 1; i >= 0; --i) {
    		int temp = digits[i] + carry;
    		digits[i] = temp % 10;
    		carry = temp / 10;
    	}
    	if (carry == 1) {
    		int ret[] = new int[len + 1];
    		for (int j = len; j > 0; --j) {
    			ret[j] = digits[j - 1];
    		}
    		ret[0] = 1;
    		for (int j = 0; j < ret.length; ++j) {
            	System.out.print(ret[j] + ",");
            }
    		return ret;
    	}
    	for (int j = 0; j < digits.length; ++j) {
        	System.out.print(digits[j] + ",");
        }
    	return digits;
    }
    
    public static ListNode rotateRight(ListNode head, int n) {//exceeds time limit
    	if (head == null || n == 0) {
            return head;
        }
    	//first, count the length
        int len = 1;
        ListNode temp = head;
        while (temp.next != null) {
        	++len;
        	temp = temp.next;
        }
        //n should be the mod of len;
        if (len <= n) {
        	n = n % len;
        }
        //then, rotate
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;
        ListNode newhead = head;
        ListNode newhead_prev = dummy;
        while (n > 0) {
            newhead = newhead.next;
            newhead_prev = newhead_prev.next;
            --n;
        }
        while (newhead.next != null) {
            p = p.next;
            newhead = newhead.next;
        }
        temp.next = head;
        newhead_prev.next = null;
        return newhead;
    }
    
    public static ListNode rotateRight2(ListNode head, int n) {//accept!!!
    	if (head == null || n == 0) {
            return head;
        }
    	ListNode p = head;
    	int len = 1;
    	while (p.next != null) {
    		p = p.next;
    		++len;
    	}
    	n = n % len;
    	n = len - n;
    	System.out.println(n);
    	p.next = head;
    	ListNode newhead = head;
    	while (n > 0) {
    		newhead = newhead.next;
    		p = p.next;
    		--n;
    	}
    	p.next = null;
    	return newhead;
    }
    
    public static void rotate(int[][] matrix) {//accepted after consulting
        int n = matrix[0].length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
    public static void test () {
    	ListNode n1 = new ListNode(1);
    	ListNode n2 = new ListNode(2);
    	ListNode n3 = new ListNode(3);
    	n1.next = n2;
    	n2.next = n3;
    	n3.next = null;
    	ListNode p = rotateRight2(n1, 1);
    	while (p != null) {
    		System.out.println(p.val);
    		p = p.next;
    	}
    }
}
