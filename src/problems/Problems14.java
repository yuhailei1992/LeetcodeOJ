package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problems14 {
	public int atoi(String str)//AC
	{
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
    
    private static void permute_tool (List<List<Integer>> ret, ArrayList<Integer> tmp, int num[], boolean[] visited) {
    	if (tmp.size() == num.length) {
    		ret.add(new ArrayList<Integer>(tmp));
    		return;
    	}
    	else {
    		for (int i = 0; i < num.length; ++i) {
    			//if (tmp.contains(num[i])) continue;
    			//else {
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
	
	public void test () {
		int num[] = {1, 1, 3};
		System.out.println(permuteUnique(num));
	}
}
