package problems;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

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
    
    public static int largestRectangleArea(int[] height) {//naive solution. O(n^2)
    	int len = height.length;
    	int max = 0;
    	int temp = 0;
    	for (int i = 0; i < len-1; ++i) {
    		for (int j = i+1; j < len; ++j) {
    			int min_height_ij = height[i];
    			for (int k = i; k <= j; ++k) {
    				if (height[k] < min_height_ij) min_height_ij = height[k];
    			}
    			temp = (j-i+1) * min_height_ij;
    			if (temp > max) max = temp;
    		}
    	}
        return max;
    }
    
    public static int largestRectangleArea2(int[] height) {//better solution. O(n) AC after consulting
    	Stack<Integer> stk = new Stack<Integer>();
    	int h[] = new int[height.length + 1];
    	h = Arrays.copyOf(height, height.length+1);
    	int maxArea = 0;
    	int i = 0;
    	while (i < h.length) {
    		if (stk.isEmpty() || h[stk.peek()] <= h[i]) {
    			stk.push(i);
    			i++;
    		}
    		else {
    			int t = stk.pop();
    			maxArea = Math.max(maxArea, h[t] * (stk.isEmpty() ? i : i - stk.peek() - 1));
    		}
    	}
        return maxArea;
        
    }
    
    public static int candy(int[] ratings) {//AC
    	
    	int len = ratings.length;
    	if (len == 1) return 1;
    	int candy[] = new int[len];
    	for (int i = 0; i < len; ++i) {
    		candy[i] = 1;
    	}
    	for (int i = 1; i < len; ++i) {
    		if (ratings[i] > ratings[i-1]) {
    			candy[i] = candy[i-1] + 1;
    		}
    	}
    	for (int i = len-2; i >= 0; --i) {
    		if (ratings[i] > ratings[i+1] && candy[i] <= candy[i+1]) 
    		    candy[i] = candy[i+1] + 1;
    	}
    	int sum = 0;
    	for (int i = 0; i < len; ++i) {
    		sum += candy[i];
    	}
        return sum;
    }
    
    public static boolean canJump(int[] A) {//naive solution, TLE
        int len = A.length;
        int temp[] = new int[len+1];
        temp[0] = 1;
        for (int i = 0; i < len; ++i) {
        	if (A[i] == 0) continue;
        	else {
        		for (int j = i+1; j < len+1 && j <= i+A[i]; ++j) {
        			temp[j] = 1;
        		}
        	}
        }
        for (int i = 0; i < len+1; ++i) {
        	System.out.println(temp[i]);
        }
        if (temp[len] == 1) return true;
        else return false;
    }
    
    public static boolean canJump2(int[] A) {//AC after consulting
    	if (A.length <= 1) return true;
    	if (A[0] > A.length-1) return true;
    	int maxlength = A[0];
    	if (maxlength == 0) return false;
    	for (int i = 1; i < A.length; ++i) {
    		if (maxlength >= i && i + A[i] >= A.length-1){
    			return true;
    		}
    		else if (maxlength <= i && A[i] == 0) {
    			return false;
    		}
    		else {
    			if (i + A[i] > maxlength) maxlength = i + A[i];
    		}
    	}
    	return false;
    }

    public static int maxProfit(int[] prices) {//AC
    	if (prices == null || prices.length < 2) return 0;
        int len = prices.length;
        int l[] = new int[len];
        int r[] = new int[len];
        int lmin = Integer.MAX_VALUE;
        int lmaxp = Integer.MIN_VALUE;
        int rmaxp = Integer.MIN_VALUE;
        int rmax = Integer.MIN_VALUE;
        
        for (int i = 0; i < len; ++i) {
        	if (prices[i] < lmin) lmin = prices[i];
        	int diff = prices[i] - lmin;
        	if (diff > lmaxp) lmaxp = diff;
        	l[i] = lmaxp;
        }
        
        for (int i = len-1; i >= 0; --i) {
        	if (prices[i] > rmax) rmax = prices[i];
        	int diff = rmax - prices[i];
        	if (diff > rmaxp) rmaxp = diff;
        	r[i] = rmaxp;
        }
        
        for (int i = 0; i < len; ++i) {
        	System.out.println(l[i]);
        }
        for (int i = 0; i < len; ++i) {
        	System.out.println(r[i]);
        }
        int maxp = Integer.MIN_VALUE;
        for (int i = 0; i < len; ++i) {
        	if (l[i] + r[i] > maxp) maxp = l[i] + r[i];
        }
        return maxp;
    }
    
    public static String minWindow(String S, String T) {//AC after three hours...
    	String minWindow = "";
    	if (S.length() < T.length()) return minWindow;
    	HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
    	for (int i = 0; i < T.length(); ++i) {
    		if (hm.containsKey(T.charAt(i))) {
    			hm.put(T.charAt(i), hm.get(T.charAt(i))+1);
    		}
    		else {
    			hm.put(T.charAt(i), 1);
    		}
    	}
    	
    	int start = 0;
    	int end = 0;
    	int cnt = 0;
    	int min = Integer.MAX_VALUE;
    	HashMap<Character, Integer> hm2 = new HashMap<Character, Integer>();
    	
    	int exist = 0;
    	while (end < S.length()) {
    		while (cnt < T.length() && end < S.length()) {
    			char curr = S.charAt(end);
	    		if (hm.containsKey(curr)) {//relevant
	    			if (hm2.containsKey(curr)) {
	    				hm2.put(curr, hm2.get(curr)+1);
	    			}
	    			else {
	    				hm2.put(curr, 1);
	    			}
	    			//then update the relevant number
	    			if (hm2.get(curr) <= hm.get(curr)) {
	    				cnt++;//valid counter
	    			}
	    		}
	    		end++;
    		}
    		//now we have a window which contains all the wanted characters
    		int isrel = 0;
    		while (cnt == T.length()) {
    			exist = 1;
    			isrel = 1;
    			char curr = S.charAt(start);
    			if (hm.containsKey(curr)) {
    				if (hm2.get(curr) == 1) {
    					hm2.remove(curr);
    					cnt--;
    					isrel = 1;
    				}
    				else {
    					if (hm2.get(curr) <= hm.get(curr)) {
    	    				cnt--;//valid counter
    	    				isrel = 1;
    	    			}
    					hm2.put(curr, hm2.get(curr)-1);
    				}
    			}
    			start++;
    		}
    		if (end - start < min) {
	    		if (isrel == 1) {
	    			minWindow = S.substring(start-1, end);
	    		}
	    		else {
	    			minWindow = S.substring(start, end);
	    		}
	    		min = end-start;
    		}
    	}
    	if (exist == 0) return "";
    	else return minWindow;
    }
    
    public static void test () {
    	String A = "a";
    	String B = "b";
    	minWindow(A, B);
    }
}
