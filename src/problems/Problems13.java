package problems;
import problems.Datastructures.TreeNode;
import java.util.*;

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
    public int atoi(String str) {
        return 0;
    }
    
    //134
    public void recoverTree(TreeNode root) {
        return;
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
    public boolean wordBreak(String s, Set<String> dict) {//AC
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
    				//dp[i][j] = true;
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
    	return dp[len][0];
    }
    
    //138
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return null;
    }
    
    //139
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return null;
    }
    
    //140
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        return null;
    }
    
    public void test () {
    	Set<String> st = new HashSet<String>();
    	st.add("leet");
    	st.add("code");
    	if (wordBreak("leetcode", st))
    	{
    		System.out.println("leetcode");
    	}
    }
}
