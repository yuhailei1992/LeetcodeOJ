package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import problems.Datastructures.*;

public class Problems2 {
    public static int maxProfit(int[] prices) {//passed after consulting the answer
        int len = prices.length;
        if (len < 1) {
            return 0;
        }
        int p = 0;
        for (int i = 1; i < len; ++i) {
            if (prices[i] > prices[i - 1]) {
                p += (prices[i] - prices[i - 1]);
            }
        }
        return p;
    }

    public int numTrees(int n) {//passed after halfly consulting the answer
        if (n == 0) {
            return 1;
        }
        else if (n < 3) {
            return n;
        }
        else {
            int retval = 0;
            for (int i = 0; i < n; ++i) {
                retval += numTrees(i) * numTrees(n - 1 - i);
            }
            return retval;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {//passed after consulting
        Stack<TreeNode> stk = new Stack<TreeNode>();
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (root == null) {
            return retlist;
        }
        else {
            TreeNode p = root;
            while (p != null || !stk.isEmpty()) {
                if (p != null) {
                    retlist.add(p.val);
                    stk.push(p);
                    p = p.left;
                }
                else {//p is null, meaning that we have reached the leftmost child. So we need to go back
                    TreeNode temp = stk.pop();
                    p = temp.right;
                }
            }
        }
        return retlist;
    }

    public List<Integer> inorderTraversal(TreeNode root) {//passed
        Stack<TreeNode> stk = new Stack<TreeNode>();
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (root == null) {
            return retlist;
        }
        else {
            TreeNode p = root;
            while (p != null || !stk.isEmpty()) {
                if (p != null) {
                    stk.push(p);
                    p = p.left;
                }
                else {//p is null, meaning that we have reached the leftmost child. So we need to go back
                    TreeNode temp = stk.pop();
                    retlist.add(temp.val);
                    p = temp.right;
                }
            }
        }
        return retlist;
    }

    public void connect(TreeLinkNode root) {//passed after consulting
        if (root == null) return;
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            if (root.next == null) {
                root.right.next = null;
            }
            else {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
    }

    public static int longestConsecutive(int[] num) {//passed after consulting
        Arrays.sort(num);
        for (int j = 0; j < num.length; ++j) {
            System.out.println(num[j]);
        }
        int len = 1;
        int templen = 1;
        int i = 1;
        while (i < num.length) {
            while (i < num.length && num[i] - num[i-1] == 1) {
                ++templen;
                ++i;
            }
            ++i;
            if (templen > len) {
                len = templen;
            }
            templen = 1;
        }
        return len;
    }

    public static int longestConsecutive2(int[] num) {
        if (num.length == 0 || num == null) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int value : num) {
            map.put(value, 0);
        }
        int maxlen = 1;
        for (int value : num) {
            if (map.get(value) == 1) {
                continue;
            }
            int templen = 1;
            int temp = value;//expand upward
            while (map.containsKey(temp + 1)) {
                templen++;
                temp++;
                map.put(temp, 1);
            }
            temp = value;//expand downward
            while (map.containsKey(temp - 1)) {
                templen++;
                temp--;
                map.put(temp, 1);
            }
            if (templen > maxlen) {
                maxlen = templen;
            }
        }
        return maxlen;
    }

    public static int removeElement(int[] A, int elem) {//passed
        if (A.length == 0) {
            return 0;
        }
        int tail = A.length - 1;
        for (int i = 0; i <= tail; ++i) {
            if (A[i] == elem) {
                while (i != tail && A[tail] == elem && tail > 0) {
                    tail--;
                }
                A[i] = A[tail];
                tail--;
            }
        }
        return tail + 1;
    }

    public boolean isBalanced(TreeNode root) {//passed without testing on ide
        if (root == null) {
            return true;
        }
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2 &&
                isBalanced(root.left) && isBalanced(root.right)) {
            return true;
        }
        else return false;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        else {
            int ldepth = maxDepth(root.left);
            int rdepth = maxDepth(root.right);
            if (ldepth > rdepth) return 1 + ldepth;
            else return 1 + rdepth;
        }
    }

    public int maxProfit1(int[] prices) {// passed after consulting the answer
        int min = 0x7fffffff;
        int max = 0;
        for (int i = 0; i < prices.length; ++i) {
            if (prices[i] < min) min = prices[i];
            int diff = prices[i] - min;
            if (diff > max) {
                max = diff;
            }
        }
        return max;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {//accepted
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;
        while (n > 0) {
            p = p.next;
            n--;
        }
        ListNode p2 = dummy;
        while (p != null) {
            p = p.next;
            p2 = p2.next;
        }
        if (p2 != null && p2.next != null) {
            p2.next = p2.next.next;
        }
        return dummy.next;
    }

    public static void testList () {
        ListNode n1 = new ListNode(1);
        ListNode t = removeNthFromEnd(n1, 1);
        while (t != null) {
            System.out.println(t.val);
            t = t.next;
        }
    }
    public static void testArray () {
        //int num[] = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        //int num1[] = {0, 1, 2, 1};
        minNumCoin ();
        //System.out.println(removeElement(num1, 1));
    }

    public static void minNumCoin () {
        int m = 11;//total number
        int coin[] = {1, 3, 5};//different coins
        int min[] = new int[12];//stores the minumum number of coins for each price
        for (int i = 0; i <= m; ++i) {
            if (i < 3) {
                min[i] = i;
            }
            else {
                int x0 = i >= coin[0] ? min[i - coin[0]] : i - 1;//i - 1 is for cases where there is 1-dollar coin
                int x1 = i >= coin[1] ? min[i - coin[1]] : i - 1;
                int x2 = i >= coin[2] ? min[i - coin[2]] : i - 1;
                min[i] = Math.min(Math.min(x0, x1), x2) + 1;
            }
        }
        for (int i = 0; i <= m; ++i) {
            System.out.println(min[i]);
        }
    }
}
