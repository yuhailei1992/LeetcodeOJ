package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import problems.Datastructures.TreeNode;

public class Problems14 {
	
    public List<String> wordBreak(String s, Set<String> dict) {
    	int len = s.length();
    	boolean dp[][] = new boolean[len+1][len];
    	dp[0][0] = true;
    	for (int i = 0; i < len; ++i)
    	{
    		for (int j = i+1; j <= len; ++j)
    		{
    			String tmp = s.substring(i, j);
    			if (dict.contains(tmp) && dp[i][0] == true)
    			{
    				dp[j][0] = true;
    			}
    		}
    	}
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
        return null;
    }
    
    public static List<List<Integer>> permuteUnique(int[] num) {//AC
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        boolean visited[] = new boolean[num.length];
        Arrays.sort(num);
        permute_tool(ret, tmp, num, visited);
        return ret;
    }
    
    private static void permute_tool (List<List<Integer>> ret, 
    		ArrayList<Integer> tmp, int num[], boolean[] visited) 
    {
    	if (tmp.size() == num.length) 
    	{
    		ret.add(new ArrayList<Integer>(tmp));
    		return;
    	}
    	else 
    	{
    		for (int i = 0; i < num.length; ++i) 
    		{
    			if (!visited[i])
    			{
    				tmp.add(num[i]);
    				visited[i] = true;
    				permute_tool(ret, tmp, num, visited);
    				tmp.remove(tmp.size()-1);
    				visited[i] = false;
    				while (i+1 < num.length && num[i] == num[i+1])
    					++i;//jump over duplicate data
    			}
    		}
    	}
    }
    public List<List<Integer>> subsetsWithDup(int[] num) {
    	return null;
    }
    
    public int findMin(int[] num) {//AC
        //
    	int start = 0;
    	int end = num.length - 1;
    	
    	while (num[start] >= num[end])
    	{
    		if (end - start == 1)
    			return num[end];
    		int mid = (start + end) / 2;
    		if (num[start] == num[mid] && num[mid] == num[end])
    		{
    			//sequential search
    			int min = num[start];
    			for (int i = start + 1; i <= end; ++i)
    			{
    				if (num[i] < min)
    					min = num[i];
    			}
    			return min;
    		}
    		else if (num[mid] >= num[start])
    		{
    			start = mid;
    		}
    		else
    		{
    			end = mid;
    		}
    	}
    	return num[start];
    }
    
    public List<List<String>> partition(String s) {//AC
    	List<List<String>> ret = new ArrayList<List<String>>();
    	List<String> tmp = new ArrayList<String>();
        sub(s, 0, ret, tmp);
        return ret;
    }
    
    private void sub (String s, int start, List<List<String>> ret, List<String> tmp)
    {
    	if (start == s.length())
    	{
    		ret.add(new ArrayList<String>(tmp));
    		return;
    	}
    	
        for (int end = start + 1; end <= s.length(); ++end)
        {
            String curr = s.substring(start, end);
            if (isValid(curr, 0, curr.length()-1))
            {
            	tmp.add(curr);
            	sub(s, end, ret, tmp);
            	tmp.remove(tmp.size()-1);
            }
        }
    }
    
    private boolean isValid (String s, int start, int end)
    {
        while (start < end)
        {
            if (s.charAt(start) != s.charAt(end))
            {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }
    
    public int minCut(String s) {//TLE
    	List<String> tmp = new ArrayList<String>();
    	int[] cut = new int[2];
        sub2(s, 0, tmp, cut);
        return cut[0];
    }
    
    private void sub2 (String s, int start, List<String> tmp, int[] cut)
    {
    	if (start == s.length()) //found a match!
    	{
    		if (cut[0] == 0)
    		{
    			cut[0] = tmp.size()-1;
    		}
    		else
    		{
    			if (tmp.size()-1 < cut[0])
    			{
    				cut[0] = tmp.size()-1;
    			}
    		}
    		return;
    	}
    	
        for (int end = start + 1; end <= s.length(); ++end)
        {
            String curr = s.substring(start, end);
            if (isValid(curr, 0, curr.length()-1))
            {
            	tmp.add(curr);
            	sub2(s, end, tmp, cut);
            	tmp.remove(tmp.size()-1);
            }
        }
    }
    
    public int minCut2(String s) {//AC
    	int len = s.length();
    	char[] s_arr = s.toCharArray();
    	int res[] = new int[len];
    	boolean mp[][] = new boolean[len][len];
    	
    	for (int start = len-1; start >= 0; --start)
    	{
    		for (int end = start; end < len; ++end)
    		{
    			if (s_arr[start] == s_arr[end] && (end - start < 2 || mp[start+1][end-1]))
    			{
    				mp[start][end] = true;
    			}
    		}
    	}
    	// 
    	for (int i = 0; i < len; ++i)
    	{
    		for (int j = 0; j < len; ++j)
    		{
    			if (mp[i][j])
    				System.out.print('O');
    			else
    				System.out.print('X');
    		}
    		System.out.println();
    	}
    	
    	for (int i = 1; i < len; ++i)
    	{
    		if (mp[0][i])
    		{
    			res[i] = 0;
    			continue;
    		}
    		int min = res[i-1] + 1;
    		for (int j = 0; j < i; ++j)
    		{
    			if (mp[j+1][i])
    			{
    				System.out.println("hey");
    				if (res[j] + 1 < min)
    					min = res[j] + 1;
    			}
    		}
    		res[i] = min;
    		
    		for (int k = 0; k < len; ++k)
        	{
        		System.out.print(res[k]);
        		
        	}
    		System.out.println();
    	}
    	for (int i = 0; i < len; ++i)
    	{
    		System.out.println(res[i]);
    		
    	}
    	return res[len-1];
    }
    
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> ret = new ArrayList<List<Integer>> ();
        if (S.length == 1) {
        	List<Integer> temp0 = new ArrayList<Integer>();
        	List<Integer> temp1 = new ArrayList<Integer>();
        	temp1.add(S[0]);
        	ret.add(temp0);
        	ret.add(temp1);
        	return ret;
        }
        else {
        	Arrays.sort(S);
            int len = S.length;
            int temp[] = Arrays.copyOf(S, len-1);
            List<List<Integer>> subret = subsets(temp);
            for (List<Integer> i : subret) {
            	ret.add(i);
            }
            for (List<Integer> j : subret) {
            	List<Integer> i2 = new ArrayList<Integer>(j);
            	i2.add(S[len-1]);
            	ret.add(i2);
            }
            return ret;
        }
    }
    /**
     * 	
		      5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
		TreeNode n0 = new TreeNode(5);
		TreeNode n1 = new TreeNode(4);
		TreeNode n2 = new TreeNode(8);
		TreeNode n3 = new TreeNode(11);
		TreeNode n4 = new TreeNode(13);
		TreeNode n5 = new TreeNode(4);
		TreeNode n6 = new TreeNode(7);
		TreeNode n7 = new TreeNode(2);
		TreeNode n8 = new TreeNode(5);
		TreeNode n9 = new TreeNode(1);
		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n5.left = n8;
		n5.right = n9;
		TreeNode m0 = new TreeNode(-2);
		TreeNode m1 = new TreeNode(-3);
		m0.left = m1;
		System.out.println(pathSum(m0, -5));
     * @param root
     * @param sum
     * @return
     */
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {//AC
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	List<Integer> tmp = new ArrayList<Integer>();
    	pathSum_sub(root, sum, ret, tmp);
        return ret;
    }
    
    private void pathSum_sub (TreeNode node, int sum, List<List<Integer>> ret, List<Integer> tmp)
    {
    	if (node == null)
    		return;
    	if (node.left == null && node.right == null)
    	{
    		if (node.val == sum)
    		{
    			tmp.add(node.val);
    			ret.add(new ArrayList<Integer>(tmp));
    			tmp.remove(tmp.size()-1);
    		}
    		return;
    	}
    	else
    	{
			if (node.left != null)
			{
				tmp.add(node.val);
				pathSum_sub(node.left, sum-node.val, ret, tmp);
    			tmp.remove(tmp.size()-1);
			}
    		
			if (node.right != null)
			{
				tmp.add(node.val);
				pathSum_sub(node.right, sum-node.val, ret, tmp);
    			tmp.remove(tmp.size()-1);
			}
    		return;
    	}
    }
    
    public int numDistinct(String S, String T) {//AC
        int dp[][] = new int[T.length()+1][S.length()+1];
        dp[0][0] = 1;
        for (int i = 1; i <= T.length(); ++i)
        	dp[i][0] = 0;
        for (int i = 1; i <= S.length(); ++i)
        	dp[0][i] = 1;
        for (int i = 1; i <= T.length(); ++i)
        {
        	for (int j = 1; j <= S.length(); ++j)
        	{
        		int tmp = dp[i][j-1];
        		if (S.charAt(j-1) == T.charAt(i-1))
        		{
        			tmp += dp[i-1][j-1];
        		}
        		dp[i][j] = tmp;
        	}
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    
	public void test () {
		String s = "rabbbit";
		String t = "rabbit";
		System.out.println(numDistinct(s, t));
	}
}
