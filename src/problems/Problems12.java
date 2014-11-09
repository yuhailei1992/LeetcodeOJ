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
    
    private boolean dfs (char[][] board, String word, int row, int col, int n, boolean[][] visited)
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
    
    public void test () {
    	
    	char[][] t = {{'A', 'B', 'C', 'E'},
    				{'S', 'F', 'C', 'S'},
    				{'A', 'D', 'E', 'E'}};
    	char[][] t2 = {{'a'}};
    	if (exist(t, "BBB"))
    	{
    		System.out.println("hello");
    	}
    }
}
