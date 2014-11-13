package problems;
import problems.Datastructures.TreeNode;

import java.util.*;
import java.util.LinkedList;

public class Problems13 {
	//131
    public double findMedianSortedArrays(int A[], int B[]) {
    	
        return 0.0;
    }
    
    //132
    public int ladderLength(String start, String end, Set<String> dict) {
        return 0;
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
    public void recoverTree(TreeNode root) {
    	if (root == null) return;
    	//TreeNode prev = null;
    	traverse(root);
        return;
    }
    
    private void traverse(TreeNode root)
    {
    	if (root == null) return;
    	traverse(root.left);
    	System.out.println(root.val);
    	traverse(root.right);
    }
    
    //135
    public boolean isMatch(String s, String p) {
        return true;
    }
    
    //136
    public boolean isScramble(String s1, String s2) {
        return true;
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
    	int num[] = {2, 2, 2};
    	int target = 4;
    	System.out.println(combinationSum2(num, target));
    }
}
