package problems;
import java.util.*;
import problems.Datastructures.Interval;

public class Problems12 {
    public boolean exist(char[][] board, String word) {//AC
    	
        System.out.println("HE");
        if (board == null || word == null)
        {
        	return false;
        }
        int m = board.length;
        int n = board[0].length;
        
        boolean visited[][] = new boolean[m][n];
        for (int i = 0; i < m; ++i) 
        {
        	for (int j = 0; j < n; ++j) 
        	{
        		if (dfs(board, word, i, j, 0, visited)) return true;
        	}
        }
        return false;
    }
    
    private boolean dfs (char[][] board, String word, int row, int col, int n, boolean[][] visited)//AC
    {
    	if (n == word.length()) return true;
    	else if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
    	{
    			return false;
    	}
    	else if (visited[row][col]) return false;
    	else if (word.charAt(n) != board[row][col]) return false;
    	visited[row][col] = true;
    	boolean ret =  (dfs(board, word, row+1, col, n+1, visited) ||
    		dfs(board, word, row-1, col, n+1, visited) ||
    		dfs(board, word, row, col+1, n+1, visited) ||
    		dfs(board, word, row, col-1, n+1, visited));
    	visited[row][col] = false;
    	return ret;
    }
    
    public String countAndSay (int n)//AC
    {
    	String init = "1";
    	for (int i = 1; i < n; ++i)
    	{
    		init = count(init);
    	}
    	return init;
    	
    }
    
    private String count(String str) {
    	char tmp[] = str.toCharArray();
    	StringBuilder ret = new StringBuilder();
    	if (str.length() == 0) return null;
    	else if (str.length() == 1) return "1" + str;
    	else
    	{
    		for (int i = 1; i < str.length(); )
    		{
    			int j = i;
    			int num = 1;
    			while (j < str.length() && tmp[j] == tmp[j-1])
    			{
    				num++;
    				j++;
    			}
    			ret.append(Integer.toString(num)).append(tmp[j-1]);
    			i = j+1;
    		}
    		if (tmp[str.length()-1] != tmp[str.length()-2])
    		{
    			ret.append("1").append(tmp[str.length()-1]);
    		}
    	}
        return ret.toString();
    }
    
    public String longestPalindrome(String s) {
        return null;
    }
    
    public int jump(int[] A) {//TLE
    	int len = A.length;
    	int temp[] = new int[len];
    	for (int i = 1; i < len; ++i)
    	{
    		temp[i] = -1;
    	}
    	int j;
    	for (int i = 0; i < A.length; ++i)
    	{
    		j = i+1;
    		while (j < A.length && j <= i + A[i])
    		{
    			if (temp[j] == -1)
    			{
    				temp[j] = temp[i] + 1;
    			}
    			else
    			{
    				temp[j] = Math.min(temp[j], temp[i]+1);
    			}
    			j++;
    		}
    	}
        return temp[len-1];
    }
    
    public int jump2(int[] A) {//AC
    	if (A == null || A.length == 0)
    	{
    		return 0;
    	}
    	else
    	{
    		int temp = 0, maxx = 0, num = 0;
    		int len = A.length;
    		for (int i = 0; i < len; )
    		{
    			if (temp >= len-1) break;
    			while (i <= temp)
    			{
    				maxx = Math.max(maxx, i+A[i]);
    				i++;
    			}
    			num++;
    			temp = maxx;
    		}
    		return num;
    	}
    }
    /**
     *  Interval x = new Interval(1, 5);
    	Interval y = new Interval(6, 8);
    	List<Interval> t = new ArrayList<Interval>();
    	t.add(x);
    	t.add(y);
    	Interval z = new Interval(5, 6);
    	List<Interval> tmp = insert(t, z);
    	for (int j = 0; j < tmp.size(); ++j)
    	{
    		System.out.println(tmp.get(j).start + " " + tmp.get(j).end);
    	}
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {//AC
        
    	List<Interval> ret = new ArrayList<Interval>();
        
        if (intervals == null || intervals.size() == 0)
        {
            ret.add(newInterval);
            return ret;
        }
        
        for (int i = 0; i < intervals.size(); ++i)
        {
        	if (newInterval.end < intervals.get(i).start) 
        	{
        		ret.add(newInterval);
        		for ( ; i < intervals.size(); ++i)
        		{
        			ret.add(intervals.get(i));
        		}
        		break;
        	}
        	else if (newInterval.start > intervals.get(i).end)
        	{
        	    ret.add(intervals.get(i));
        		continue;
        	}
        	else if (hasOverlap(intervals.get(i), newInterval))
        	{
        	    Interval curr = new Interval(Math.min(intervals.get(i).start, newInterval.start), 
        				Math.max(intervals.get(i).end, newInterval.end));
        		//merge afterwards
        		int j = i+1;
        		while (j < intervals.size() && hasOverlap(curr, intervals.get(j)))
        		{
        		    //update curr
        			curr.end = Math.max(curr.end, intervals.get(j).end);
        			j++;
        		}
        		ret.add(curr);
        		while (j < intervals.size())
        		{
        		    ret.add(intervals.get(j));
        		    ++j;
        		    break;
        		}
        	}
        }
        if (newInterval.start > intervals.get(intervals.size()-1).end)
        {
        	ret.add(newInterval);
        }
        return ret;
    
    }
    
    private boolean hasOverlap(Interval obj1, Interval obj2)
    {
    	if (obj1.end < obj2.start) return false;
    	if (obj1.start > obj2.end) return false;
    	else return true;
    }
    //125
    public int maxProduct(int[] A) {//AC
        int min_local = A[0];
        int max_local = A[0];
        int max_global = A[0];
        for (int i = 1; i < A.length; ++i)
        {
        	int min_local_copy = min_local;
        	min_local = Math.min(Math.min(A[i], A[i] * min_local), A[i] * max_local);
        	max_local = Math.max(Math.max(A[i], A[i] * min_local_copy), A[i] * max_local);
        	max_global = Math.max(max_global, max_local);
        }
        return max_global;
    }
    
    //126
    public void nextPermutation(int[] num) {//AC
    	 if (num == null || num.length < 2)
         {
         	return;
         }
         else
         {
         	int len = num.length;
         	int i = len-1;
         	while (i >= 1 && num[i-1] >= num[i])
         	{
         		--i;
        		}
         	--i;
         	if (i == -1 && num[0] >= num[1])
         	{
         		Arrays.sort(num);
         		return;
         	}
         	int j = i + 1;
         	int min_above_index = i + 1;
         	while (j < len)
         	{
         		if (num[j] < num[min_above_index] && num[j] > num[i])
         		{
         			min_above_index = j;
         		}
         		++j;
         	}
         	int temp = num[i];
         	num[i] = num[min_above_index];
         	num[min_above_index] = temp;
         	int temp_array[] = new int[len - i - 1];
         	//sort the second part
         	for (int k = 0; k < len-1-i; ++k)
         	{
         		temp_array[k] = num[i + k + 1];
         	}
         	Arrays.sort(temp_array);
         	for (int k = 0; k < temp_array.length; ++k)
         	{
         		num[i+k+1] = temp_array[k];
         	}
         }
    }

    public void test () {
    	int num[] = {5, 1, 1};
    	nextPermutation(num);
    	for (int x = 0; x < num.length; ++x)
    	{
    		System.out.println(num[x]);
    	}
    }
}
