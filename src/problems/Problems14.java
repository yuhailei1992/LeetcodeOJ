package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
	public void test () {
		int num[] = {1, 2, 1, 1};
		System.out.println(findMin(num));
	}
}
