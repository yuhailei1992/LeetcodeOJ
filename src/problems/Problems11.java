package problems;
import java.util.*;
import problems.Datastructures.*;

public class Problems11 {
    public static List<String> restoreIpAddresses(String s) {//AC
    	int len = s.length();
    	if (len < 4 || len > 12) return new ArrayList<String>();
        return subIP(s, 4);
    }
    
    private static ArrayList<String> subIP (String str, int n) {
    	ArrayList<String> ret = new ArrayList<String>();
    	if (str.length() == 0 || str.length() > n * 3) {
    		return ret;
    	}
    	else {
    		for (int i = Math.min(3, str.length()); i > 0; --i) {
    			String first_num = str.substring(0, i);
    			if (isValid(first_num) && str.length()-i <= 3 * (n-1)) {
    				
    				//check the rest
    				if (n-1 == 0) {
    					ret.add(first_num);
    				}
    				else {
	    				ArrayList<String> sub_ret = subIP(str.substring(i), n-1);
	    				if (sub_ret.size() != 0) {//valid return
	    					//System.out.println(sub_ret.size() + "n" + n);
	    					for (int j = 0; j < sub_ret.size(); ++j) {
	    						String temp = first_num + "." + sub_ret.get(j);
	    						ret.add(temp);
	    					}
	    				}
    				}
    			}
    		}
    	}
    	return ret;
    }
    
    private static boolean isValid (String str) {
    	if (str == null || str.length() == 0 || str.length() > 3) return false;
    	else {
    		if (str.length() > 1 && str.charAt(0) == '0') return false;
    		else if (Integer.parseInt(str) > 255) return false;
    		else return true;
    	}
    }
    
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        return null;
    }
    
    public static void test () {
    	String s = "2552552550";
    	System.out.println(restoreIpAddresses(s));
    }
}
