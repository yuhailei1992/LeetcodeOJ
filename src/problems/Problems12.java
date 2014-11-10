package problems;

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
    
    
    public String count(String str) {
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
    
    public String countAndSay2(int n) {
        if (n<1){
            return null;
        }
        int i=2;
        String current="1";
        while (i<=n){
            current=say(current);
            i++;
        }
        
        return current;
    }
    
    // count each char in given input string
    private String say(String input){
        char last=input.charAt(0);
        
        String result="";
        
        int i=1;// index
        
        int count=1;// count for each char
        while (i<input.length()){
            if (input.charAt(i)==last){
                count++;
            }
            else{
                result+=count;
                result+=last;
                
                last=input.charAt(i);
                count=1;
            }
            i++;
        }
        result+=count;
        result+=last;
        
        return result;
        
    }
    public void test () {
    	System.out.println(countAndSay(9));
    	System.out.println(countAndSay2(4));
    }
}
