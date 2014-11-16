package problems;
import problems.Datastructures.TreeNode;

import java.util.*;
import java.util.LinkedList;

public class Problems13 {
	//131
    public double findMedianSortedArrays(int A[], int B[]) {//AC
    	int total = A.length + B.length;
    	if (total % 2 == 1)
    	{
    		return find(A, A.length, B, B.length, (A.length+B.length)/2+1);
    	}
    	else 
    	{
    		return (find(A, A.length, B, B.length, (A.length+B.length)/2) +
    				find(A, A.length, B, B.length, (A.length+B.length)/2+1)) / 2;
    	}
    }
    
    private double find (int a[], int m, int b[], int n, int k)
    {
    	if (m > n)
    		return find(b, n, a, m, k);
    	if (m == 0)
    	{
    		return b[k-1];
    	}
    	if (k == 1)
    	{
    		return Math.min(a[0],  b[0]);
    	}
    	int pa = Math.min(m, k / 2);
    	int pb = k - pa;
    	if (a[pa-1] < b[pb-1])
    	{
    		int[] tmp = Arrays.copyOfRange(a, pa, a.length);
    		return find(tmp, m - pa, b, n, k - pa);
    	}
    	else if (a[pa-1] > b[pb-1])
    	{
    		int[] tmp = Arrays.copyOfRange(b, pb, b.length);
    		return find(a, m, tmp, n - pb, k - pb);
    	}
    	else //equal
    		return a[pa-1];
    }
    
    //132
    public int ladderLength(String start, String end, Set<String> dict) {//AC
    	
    	if (start == null || end == null || dict == null || dict.size() == 0)
    		return 0;
    	
    	Queue<String> queue = new LinkedList<String>();
    	queue.offer(start);
    	dict.remove(start);
    	int len = 1;
    	
    	while (!queue.isEmpty())
    	{
    		int count = queue.size();
    		for (int i = 0; i < count; ++i)
    		{
    			String curr_str = queue.poll();
    			for (int j = 0; j < curr_str.length(); ++j)
    			{
    				for (char c = 'a'; c <= 'z'; ++c)
    				{
    					if (c == curr_str.charAt(j))
    						continue;
    					String tmp = replace(curr_str, j, c);
    					if (tmp.equals(end))
    						return len + 1;
    					if (dict.contains(tmp))
    					{
    						queue.offer(tmp);
    						dict.remove(tmp);
    					}
    				}
    			}
    		}
    		len++;
    	}
        return 0;
    }
    
    private String replace(String s, int i, char to_sub)
    {
    	char[] tmp = s.toCharArray();
    	tmp[i] = to_sub;
    	return tmp.toString();
    }
    
    //133
    public int atoi(String str) {//AC
    	if (str == null || str.length() == 0)
		{
			return 0;
		}
		str = str.trim();
		char[] str_arr = str.toCharArray();
		double val = 0;
		int i = 0;
		if (str_arr[0] == '+') ++i;
		else if (str_arr[0] == '-') ++i;
		for ( ; i < str_arr.length; ++i)
		{
			if (str_arr[i] < '0' || str_arr[i] > '9') break;
			val *= 10;
			val += (str_arr[i] - 48);
		}
		if (str_arr[0] == '-')
		{
			val = -val;
		}
		if (str_arr[0] != '-' && val + Integer.MAX_VALUE + 1 == 0)
			val = Integer.MAX_VALUE;
		return (int)val;
    }
    
    //134
    /**
     *  TreeNode n1 = new TreeNode(2);
    	TreeNode n2 = new TreeNode(4);
    	TreeNode n3 = new TreeNode(5);
    	n1.left = n2;
    	n1.right = n3;
    	recoverTree(n1);
     * @param root
     */
    
    TreeNode pre;
    TreeNode first;
    TreeNode second;
    
    public void recoverTree(TreeNode root) {//AC
    	pre = null;
    	first = null;
    	second = null;
    	
    	traverse(root);
    	if (first != null && second != null)
    	{
    		int tmp = first.val;
    		first.val = second.val;
    		second.val = tmp;
    	}
    }
    
    private void traverse(TreeNode root)
    {
    	if (root == null) return;
    	traverse(root.left);
    	if (pre == null)
    		pre = root;
    	else
    	{
    		if (pre.val > root.val)
    		{
    			if (first == null)
    				first = pre;
    			second = root;
    		}
    		pre = root;
    	}
    	traverse(root.right);
    }
    
    //135
    public boolean isMatch(String s, String p) {//AC
    	if (p.length() == 0)
    		return s.length() == 0;
    	if (p.length() == 1)
    	{
    		if (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
    			return true;
    		else
    			return false;
    	}
    	if (p.charAt(1) != '*')
    	{
    		if (s.length() < 1)
    			return false;
    		else if ((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && 
    				isMatch(s.substring(1), p.substring(1)))
    			return true;
    		else return false;
    	}
    	while (s.length() > 0 && 
    			(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
    	{
    		if (isMatch(s, p.substring(2))) return true;
    		s = s.substring(1);
    	}
    	return isMatch(s, p.substring(2));
    }
    
    public boolean isMatch2(String s, String p)//wrong
    {
    	if (s == null && p == null)
    		return true;
    	int i = 0, j = 0;
    	int len_s = s.length();
    	int len_p = p.length();
    	while (i < len_s && j < len_p)
    	{
    		if (s.charAt(i) == p.charAt(j))
    		{
    			++i;
    			++j;
    		}
    		else if (p.charAt(j) == '.')
    		{
    			++i;
    			++j;
    		}
    		else if (p.charAt(j) == '*')
    		{
    			if ((i > 0 && j > 0) && 
    			    ((s.charAt(i) == s.charAt(i-1) && p.charAt(j-1) == s.charAt(i-1)) ||
    			     p.charAt(j-1) == '.'))
    			{
    				++i;
    				++j;
    			}
    			else return false;
    		}
    		else if (j + 1 < len_p && p.charAt(j+1) == '*')
    		{
    			j += 2;
    		}
    		else return false;
    	}
    	if (i == len_s && j == len_p)
    		return true;
    	else 
    		return false;
    }
    
    
    //136
    public boolean isScramble(String s1, String s2) {
    	if (s1.length() != s2.length()) return false;
    	if (s1.equals(s2)) return true;
    	
    	int L = s1.length();
    	int[] chars = new int[26];
    	for (int i = 0; i < L; ++i)
    	{
    		chars[s1.charAt(i)-'a']++;
    		chars[s2.charAt(i)-'a']--;
    	}
    	
    	for (int i = 0; i < 26; ++i)
    	{
    		if (chars[i] != 0)
    			return false;
    	}
    	
    	for (int i = 1; i < L; ++i)
    	{
    		String s11 = s1.substring(0, i);
    		String s21 = s2.substring(0, i);
    		String s12 = s1.substring(i, L);
    		String s22 = s2.substring(i, L);
    		if (isScramble(s11, s21) && isScramble(s12, s22))
    		{
    			return true;
    		}
    		// or it is twisted
    		s21 = s2.substring(0, L-i);
    		s22 = s2.substring(L-i, L);
    		if (isScramble(s11, s22) && isScramble(s12, s21))
    		{
    			return true;
    		}
    	}
        return false;
    }
    
    //137
    /*
     *  Set<String> st = new HashSet<String>();
    	st.add("leet");
    	st.add("code");
    	if (wordBreak("leetcode", st))
    	{
    		System.out.println("leetcode");
    	}
     */
    public List<String> wordBreak(String s, Set<String> dict) {//TLE
    	int len = s.length();
    	boolean dp[][] = new boolean[len+1][len+1];
    	dp[0][0] = true;
    	for (int i = 0; i < len; ++i)
    	{
    		for (int j = i+1; j <= len; ++j)
    		{
    			String tmp = s.substring(i, j);
    			if (dict.contains(tmp) && dp[i][0] == true)
    			{
    				dp[i][j] = true;
    				dp[j][0] = true;
    			}
    		}
    	}
    	// print the dp map
    	for (int i = 0; i < dp.length; ++i)
    	{
    		for (int j = 0; j < dp[0].length; ++j)
    		{
    			if (dp[i][j])
    				System.out.print('O');
    			else 
    				System.out.print('X');
    		}
    		System.out.println();
    	}
    	
    	List<String> ret = new ArrayList<String>();
    	
    	while(hasMore(dp, 0)) {
    		int i = 0;
    		StringBuilder tmp = new StringBuilder();
	    	loop1:
	    		
	    	while (i <= len)
	    	{
	    		for (int j = i+1; j <= len; ++j)
	    		{
	    			if (dp[i][j])
	    			{
	    				dp[i][j] = false;
	    				//dp[j][0] = false;
	    				tmp.append(s.substring(i, j)).append(" ");
	    				i = j;
	    				j = 0;
	    				System.out.println("i" + i + "j" + j);
	    				if (i == len) break loop1;
	    				else if (hasMore(dp, i)) continue loop1;
	    				else break loop1;
	    			}
	    		}
	    	}
    		for (int k = 0; k < dp.length; ++k)
        	{
        		for (int j = 0; j < dp[0].length; ++j)
        		{
        			if (dp[k][j])
        				System.out.print('O');
        			else 
        				System.out.print('X');
        		}
        		System.out.println();
        	}
    		tmp.deleteCharAt(tmp.length()-1);
	    	System.out.println(tmp.toString());
	    	if ((tmp.charAt(tmp.length()-1)) == s.charAt(s.length()-1))
	    		ret.add(tmp.toString());
    	}
    	return ret;
    }
    private boolean hasMore (boolean[][]dp, int level)
    {
    	for (int i = 1; i < dp[0].length; ++i)
    	{
    		if (dp[level][i])
    			return true;
    	}
    	return false;
    }
    
    /*
     *  HashSet<String> dict = new HashSet<String>();
    	dict.add("a");
    	dict.add("abc");
    	dict.add("b");
    	dict.add("cd");
    	System.out.println(wordBreak2("abcd", dict));
     */
    
    public List<String> wordBreak2(String s, Set<String> dict)//AC
    {
    	if (s == null || s.length() == 0)
    		return new ArrayList<String>();
    	
    	int len = s.length();
    	List<Integer>[] lists = new ArrayList[len];
    	for (int i = 0; i < len; ++i)
    	{
    		lists[i] = new ArrayList<Integer>();
    	}
    	
    	for (int end = 1; end <= len; ++end)
    	{
    		for (int start = 0; start < end; ++start)
    		{
    			String tmp = s.substring(start, end);
    			if (dict.contains(tmp))
    			{
    				if (start == 0 || !lists[start-1].isEmpty())
    				{
    					lists[end-1].add(start);
    				}
    			}
    		}
    	}
    	return rebuildList(lists, s, len-1);
    }
    
    private List<String> rebuildList(List<Integer>[] lists, String s, int end)
    {
    	List<String> ret = new ArrayList<String>();
    	for (int i = 0; i < lists[end].size(); ++i)
    	{
    		int start = lists[end].get(i);
    		String tmp = s.substring(start, end+1);
    		if (start == 0)
    		{
    			ret.add(tmp);
    		}
    		else 
    		{
    			List<String> sub = rebuildList(lists, s, start-1);
    			for (String str : sub)
    			{
    				ret.add(str + " " + tmp);
    			}
    		}
    	}
    	return ret;
    }
    
    //138
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	
        return null;
    }
    
    //139
    public List<List<Integer>> combinationSum(int[] candidates, int target) {//AC
    	Arrays.sort(candidates);
    	return combine_helper(candidates, target, 0);
    }
    
    private List<List<Integer>> combine_helper (int[] candidates, int target, int start)
    {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	for (int i = start; i < candidates.length; ++i)
    	{
        	if (candidates[i] > target)
        	{
        		return ret;
        	}
        	else if (candidates[i] == target)
        	{
        		System.out.println("equal");
        		ArrayList<Integer> tmp = new ArrayList<Integer>();
        		tmp.add(candidates[i]);
        		ret.add(tmp);
        		return ret;
        	}
        	else
        	{
	        	System.out.println(target - candidates[i]);
				List<List<Integer>> sub = 
						combine_helper(candidates, target-candidates[i], i);
				System.out.println(sub);
				if (sub != null && sub.size() != 0)
				{
	    			for (List<Integer> ls : sub)
	    			{
	    				ArrayList<Integer> tmp = new ArrayList<Integer>();
	    				tmp.add(candidates[i]);
	    				System.out.println(candidates[i]);
	    				tmp.addAll(ls);
	    				System.out.println(tmp);
	    				ret.add(tmp);
	    			}
	    			//return ret;
				}
        	}
    	}
    	return ret;
    }
    
    //140
    public List<List<Integer>> combinationSum2(int[] num, int target) {//AC
    	Arrays.sort(num);
    	return combine_helper2(num, target, 0);
    }
    
    private List<List<Integer>> combine_helper2 (int[] candidates, int target, int start)
    {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	for (int i = start; i < candidates.length; ++i)
    	{
        	if (candidates[i] > target)
        	{
        		return ret;
        	}
        	else if (candidates[i] == target)
        	{
        		System.out.println("equal");
        		ArrayList<Integer> tmp = new ArrayList<Integer>();
        		tmp.add(candidates[i]);
        		ret.add(tmp);
        		return ret;
        	}
        	else
        	{
	        	System.out.println(target - candidates[i]);
				List<List<Integer>> sub = 
						combine_helper2(candidates, target-candidates[i], i+1);
				System.out.println(sub);
				if (sub != null && sub.size() != 0)
				{
	    			for (List<Integer> ls : sub)
	    			{
	    				ArrayList<Integer> tmp = new ArrayList<Integer>();
	    				tmp.add(candidates[i]);
	    				System.out.println(candidates[i]);
	    				tmp.addAll(ls);
	    				System.out.println(tmp);
	    				if (!ret.contains(tmp))
	    					ret.add(tmp);
	    			}
				}
        	}
    	}
    	return ret;
    }
    
    public void test () {
    	String s = "aaa";
    	String p = "ab*bbbcd";
    	if (isMatch(s, p))
    	{
    		System.out.println("hey");
    	}
    }
}
