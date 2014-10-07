package problems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import problems.Datastructures.*;
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

    public static int[][] generateMatrix(int n) {
        //int num[][] = new int[n][n];
        return null;
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
        //if ((long)mid * (long) mid > x) mid--;
        return mid;
    }

    public static ListNode mergeKLists(List<ListNode> lists) {//TLE
        if (lists == null) return null;
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int min_idx = 0;
        int min_val;
        int depleted_cnt;
        ListNode to_insert;
        ListNode t;
        while (true) {
            min_val = Integer.MAX_VALUE;
            depleted_cnt = 0;
            for (int i = 0; i < lists.size(); ++i) {
                if (lists.get(i) == null) {
                    depleted_cnt++;
                }
                else {
                    if (lists.get(i).val < min_val) {
                        min_idx = i;
                    }
                }
            }
            if (depleted_cnt == lists.size()) {
                break;
            }
            //insert
            to_insert = lists.get(min_idx);
            lists.set(min_idx, to_insert == null? null:to_insert.next);
            t = new ListNode(to_insert.val);
            tail.next = t;
            t.next = null;
            tail = t;
        }
        return dummy.next;
    }

    public static ListNode mergeKLists2(List<ListNode> lists) {
        return null;
    }

    /*public static List<Interval> merge(List<Interval> intervals) {
        return null;
    }*/
    public static int firstMissingPositive(int[] A) {//AC after consulting
        int len = A.length;
        int i = 0;
        while (i < len) {
            if (A[i] != i+1 && A[i] >= 1 && A[i] <= len && A[A[i]-1] != A[i]) {
                int temp = A[A[i]-1];
                A[A[i]-1] = A[i];
                A[i] = temp;
            }
            else {
                ++i;
            }
        }
        for (i = 0; i < len; ++i) {
            if (A[i] != i+1) return i+1;
        }
        return len+1;
    }

    /**
     * Don't know if the list is sorted.
     * So, sort it first, then do the rest.
     * @param intervals
     * @return
     */
    public static List<Interval> merge(List<Interval> intervals) {//AC
        if (intervals == null) return null;
        int len = intervals.size();
        if (len < 2) return intervals;

        Comparator<Interval> BY_HEAD = new Comparator<Interval> () {
            public int compare(Interval s1, Interval s2) {
                return (int)(s1.start - s2.start);
            }
        };
        Collections.sort(intervals, BY_HEAD);

        ArrayList<Interval> ret = new ArrayList<Interval>();
        int j = 0;
        int i = 0;
        while (i < len) {
            int start = intervals.get(i).start;
            int end = intervals.get(i).end;
            j = i;
            while (j < len-1 && intervals.get(j+1).start <= end) {
                if (intervals.get(j+1).end > end) {
                    end = intervals.get(j+1).end;
                }
                ++j;
            }
            ret.add(new Interval(start, end));
            i = j+1;
        }
        return ret;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {//AC
        if (matrix == null) return null;

        //corner case 2
        List<Integer> result = new ArrayList<Integer>();
        int row = matrix.length;
        if (row == 0) return result;

        int col = matrix[0].length;
        if (col == 0) return result;

        //regular
        int start_row = 0, end_row = row-1, start_col = 0, end_col = col-1;

        while (start_row <= end_row && start_col <= end_col) {
            subSpiral(result, matrix, start_row, end_row, start_col, end_col);
            start_row++;
            end_row--;
            start_col++;
            end_col--;
        }
        return result;
    }

    public static void subSpiral (List<Integer> result, int[][] matrix, int start_row, int end_row, int start_col, int end_col) {

        for (int j = start_col; j <= end_col; ++j) {
            result.add(matrix[start_row][j]);
        }

        for (int i = start_row+1; i <= end_row; ++i) {
            result.add(matrix[i][end_col]);
        }
        if (start_row != end_row) {
            for (int j = end_col-1; j >= start_col; --j) {
                result.add(matrix[end_row][j]);
            }
        }
        if (start_col != end_col) {
            for (int i = end_row-1; i > start_row; --i) {
                result.add(matrix[i][start_col]);
            }
        }
    }


    public static int[][] generateMatrix2(int n) {//problem 79
        if (n <= 0) return new int[0][0];
        int ret[][] = new int[n][n];
        int start = 0;
        int end = n-1;
        int pivot = 0;
        while (start <= end) {
            pivot = subMatrix (ret, pivot, start, end);
            start++;
            end--;
        }
        return ret;

    }

    public static int subMatrix (int[][] matrix, int pivot, int start, int end) {
        if (start == end) {
            matrix[start][start] = ++pivot;
            return pivot;
        }
        int cnt = pivot;
        for (int i = start; i < end; ++i) {
            cnt++;
            matrix[start][i] = cnt;
        }
        for (int i = start; i < end; ++i) {
            cnt++;
            matrix[i][end] = cnt;
        }
        for (int i = end; i > start; --i) {
            cnt++;
            matrix[end][i] = cnt;
        }
        for (int i = end; i > start; --i) {
            cnt++;
            matrix[i][start] = cnt;
        }
        return cnt;
    }

    public static int[] twoSum(int[] numbers, int target) {//AC
        if (numbers == null || numbers.length < 2) return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int ret[] = {0, 0};
        for (int i = 0; i < numbers.length; ++i) {
            if (!hm.containsKey(target - numbers[i])) {
                hm.put(numbers[i], i);
            }
            else {
                int tmp1 = hm.get(target - numbers[i]) + 1;
                int tmp2 = i + 1;

                if (tmp1 < tmp2) {
                    ret[0] = tmp1;
                    ret[1] = tmp2;
                }
                else {
                    ret[0] = tmp2;
                    ret[1] = tmp1;
                }
                return ret;
            }
        }
        return ret;
    }

    public static void test () {
        int arr[] = {2, 7, 9, 11};
        twoSum(arr, 9);
    }
}
