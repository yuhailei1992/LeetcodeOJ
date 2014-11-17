package problems;
import java.util.*;

import problems.Datastructures.TreeNode;

public class Problems15 {
	//151
    public TreeNode buildTree(int[] preorder, int[] inorder) {//AC
    	int p_start = 0;
    	int p_end = preorder.length-1;
    	
    	int i_start = 0;
    	int i_end = inorder.length-1;
    	return build_sub(preorder, p_start, p_end, inorder, i_start, i_end);
    }
    
    private TreeNode build_sub (int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end)
    {
    	if (p_start > p_end || i_start > i_end)
    		return null;
    	
    	TreeNode root = new TreeNode(preorder[p_start]);
    	int index = 0;
    	for (int i = i_start; i <= i_end; ++i)
    	{
    		if (inorder[i] == preorder[p_start])
    		{
    			index = i;
    			break;
    		}
    	}
    	// now we have the index of root node in the inorder traversal
    	int len = index - i_start;
    	root.left = build_sub(preorder, p_start+1, p_start+1+len-1, inorder, i_start, index-1);
    	root.right = build_sub(preorder, p_start+1+len, p_end, inorder, index+1, i_end);
    	return root;
    }
    
    /*
     * lesson learned: do not use absolute indexes
     */
    //152
    public TreeNode buildTree2(int[] inorder, int[] postorder) {//AC
    	int i_start = 0;
    	int i_end = inorder.length-1;
    	int p_start = 0;
    	int p_end = postorder.length-1;
    	
    	return build_sub2(inorder, i_start, i_end, postorder, p_start, p_end);
    }
    
    private TreeNode build_sub2 (int[] inorder, int i_start, int i_end, int[] postorder, int p_start, int p_end)
    {
    	if (i_start > i_end || p_start > p_end)
    		return null;
    	
    	TreeNode root = new TreeNode(postorder[p_end]);
    	
    	int index = 0;
    	for (int i = i_start; i <= i_end; ++i)
    	{
    		if (inorder[i] == postorder[p_end])
    		{
    			index = i;
    			break;
    		}
    	}
    	
    	// now we have the index of root node in the inorder traversal
    	int len = index - i_start;
    	root.left = build_sub2(inorder, i_start, index-1, postorder, p_start, p_start+len-1);
    	root.right = build_sub2(inorder, index+1, i_end, postorder, p_start+len, p_end-1);
    	return root;
    }
    
    //153
    public boolean isNumber1(String s) {//wrong
        if (s == null) return false;
    	String str = s.trim();
    	int len = str.length();
    	int exp = 0;
    	int decimal = 0;
    	int neg = 0;
    	if (len == 0) return false;
    	//can only begin with numbers
    	if (len == 1 && !(str.charAt(0) >= '0' && str.charAt(0) <= '9')) return false;
    	if (!(str.charAt(0) >= '0' && str.charAt(0) <= '9') && str.charAt(0) != '.' && str.charAt(0) != '-' && str.charAt(0) != '+') {
    		return false;
    	}
    	for (int i = 0; i < str.length(); ++i) {
    		char curr = str.charAt(i);
    		if (curr == ' ') {
    			return false;
    		}
    		else if (curr == '-' || curr == '+') {
    		    if (i != 0) {
    		        return false;
    		    }
    		}
    		else if (curr == 'e') {
    		    if (exp == 1 || decimal == 1) return false;
    		    exp = 1;
    			if (i == len-1) return false;
    			else {
    				if (!(str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9')) {
    					return false;
    				}
    			}
    		}
    		else if (curr == '.') {
    		    if (exp == 1 || decimal == 1) return false;
    		    decimal = 1;
    			if (i != len-1 && (!(str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9'))) {
    				return false;
    			}
    		}
    		else if (!(curr >= '0' && curr <= '9')) return false;
    	}
        return true;
    }
    
    public boolean isNumber (String s) {//graceful solution. Copied from internet
    	int table[][] = {
    			{-1,  0,  3,  1,  2, -1} ,
                {-1,  8, -1,  1,  4,  5} ,
                {-1, -1, -1,  4, -1, -1} ,
                {-1, -1, -1,  1,  2, -1} ,
                {-1,  8, -1,  4, -1,  5} ,
                {-1, -1,  6,  7, -1, -1} ,
                {-1, -1, -1,  7, -1, -1} ,
                {-1,  8, -1,  7, -1, -1} ,
                {-1,  8, -1, -1, -1, -1}
    	};
    	int last = 0;
    	for (int i = 0; i < s.length(); ++i)
    	{
    		char curr = s.charAt(i);
    		int state = 0;
    		if (curr == ' ')
    			state = 1;
    		else if (Character.isDigit(curr))
    			state = 3;
    		else if (curr == '+' || curr == '-')
    			state = 2;
    		else if (curr == '.')
    			state = 4;
    		else if (curr == 'e' || curr == 'E')
    			state = 5;
    		last = table[last][state];
    		if (last == 0) return false;
    		
    	}
    	if (last == 3 || last == 6 || last == 7 || last == 8)
    		return true;
    	else
    		return false;
    }
    //155 copied from blog
        
    class StringWithLevel {
    	String str;
    	int level;
    	public StringWithLevel(String str, int level)
    	{
    		this.str = str;
    		this.level = level;
    	}
    }
        
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        ArrayList<List<String>> res = new ArrayList<List<String>>();
        HashSet<String> unvisited = new HashSet<String>();
        unvisited.addAll(dict);
        unvisited.add(start);
        unvisited.remove(end);
        
        Map<String, List<String>> next = new HashMap<String, List<String>>();
        for (String e : unvisited)
        {
        	next.put(e,  new ArrayList<String>());
        }
        
        java.util.LinkedList<StringWithLevel> queue = new java.util.LinkedList<StringWithLevel>();
        queue.add(new StringWithLevel(end, 0));
        boolean found = false;
        int finallevel = Integer.MAX_VALUE;
        int curlevel = 0;
        int prelevel = 0;
        HashSet<String> visited = new HashSet<String> ();
        while (!queue.isEmpty())
        {
        	StringWithLevel cur = queue.poll();
        	String cur_str = cur.str;
        	curlevel = cur.level;
        	if (found && curlevel > finallevel)
        	{
        		break;
        	}
        	if (curlevel > prelevel)
        	{
        		unvisited.removeAll(visited);
        	}
        	prelevel = curlevel;
        	char[] curarr = cur_str.toCharArray();
        	for (int i = 0; i < curarr.length; ++i)
        	{
        		char orig_char = curarr[i];
        		boolean foundcurcycle = false;
        		for (char c = 'a'; c <= 'z'; ++c)
        		{
        			curarr[i] = c;
        			String newstr = new String(curarr);
        			if (c != orig_char && unvisited.contains(newstr))
        			{
        				next.get(newstr).add(cur_str);
        				if (newstr.equals(start))
        				{
        					found = true;
        					finallevel = curlevel;
        					foundcurcycle = true;
        					break;
        				}
        				if (visited.add(newstr))
        				{
        					queue.add(new StringWithLevel(newstr, curlevel+1));
        				}
        			}
        		}
        		if (foundcurcycle) break;
        		curarr[i] = orig_char;
        	}
        }
        if (found)
    	{
    		ArrayList<String> list = new ArrayList<String>();
    		list.add(start);
    		getPaths(start, end, list, finallevel+1, next, res);
    	}
    	return res;
        
    }
    
    private void getPaths(String cur, String end, ArrayList<String> list, int level, Map<String, List<String>> next, ArrayList<List<String>> res)
    {
    	if (cur.equals(end))
    	{
    		res.add(new ArrayList<String>(list));
    	}
    	else if (level > 0)
    	{
    		List<String> parentsSet = next.get(cur);
    		for (String parent : parentsSet) {
    			list.add(parent);
    			getPaths(parent, end, list, level-1, next, res);
    			list.remove(list.size()-1);
    		}
    	}
    }

    public void test () 
    {
    	int inorder[] = {1, 2, 3, 4, 5};
    	int postorder[] = {2, 3, 1, 5, 4};
    	buildTree2(inorder, postorder);
    }
}
