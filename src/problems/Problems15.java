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
    
    //152
    
    public void test () 
    {
    	int inorder[] = {1, 2, 3, 4, 5};
    	int postorder[] = {2, 3, 1, 5, 4};
    	buildTree2(inorder, postorder);
    }
}
