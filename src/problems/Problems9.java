package problems;
import problems.Datastructures.RandomListNode;
public class Problems9 {
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
    
    public static int minDistance(String word1, String word2) {
        return 0;
    }
    
    public static String convert(String s, int nRows) {//AC
    	if (nRows == 1) return s;
    	StringBuilder ret = new StringBuilder();
    	int len = s.length();
    	int step = 2 * nRows - 2;
    	for (int i = 0; i < nRows; ++i) {
    		if (i == 0 || i == nRows-1) {
    			for (int j = i; j < len; j+=step) {
    				ret.append(s.charAt(j));
    			}
    		}
    		else {
    			int step1 = (nRows-1-i) * 2;
    			int step2 = step - step1;
    			int flag = 0;
    			for (int j = i; j < len;) {
    				if (flag == 0) {
    					ret.append(s.charAt(j));
    					j += step1;
    					flag = 1;
    				}
    				else {
    					ret.append(s.charAt(j));
    					j += step2;
    					flag = 0;
    				}
    			}
    		}
    	}
        return ret.toString();
    }
    
    public static RandomListNode copyRandomList(RandomListNode head) {//AC
    	if (head == null) return null;
        RandomListNode p = head;
        while (p != null) {
        	RandomListNode p_dup = new RandomListNode(p.label);
        	p_dup.next = p.next;
        	p.next = p_dup;
        	p = p_dup.next;
        }
        p = head;
        RandomListNode p_next;
        while (p != null) {
        	p_next = p.next;
        	if (p.random != null) p_next.random = p.random.next;
        	p = p_next.next;
        }
        p = head;
        RandomListNode newhead = head == null ? null : p.next;
        RandomListNode newtail = newhead;
        while (p != null) {
        	p.next = newtail.next;
        	if (newtail.next != null) newtail.next = newtail.next.next;
        	p = p.next;
        	newtail = newtail.next;
        }
        return newhead;
    }
    
    public static void test () {
    	
    }
}
