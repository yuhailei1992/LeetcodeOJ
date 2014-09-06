package problems;

public class Problems3 {
	public static int romanToInt(String s) {//accepted
        if (s == null) {
            return 0;
        }
        int len = s.length();
        int val = 0;
        for (int i = 0; i < len; ++i) {
        	val += c2n(s.charAt(i));
        	int t = i;
        	while (t > 0 && c2n(s.charAt(t)) > c2n(s.charAt(t-1))) {
        		val -= 2*c2n(s.charAt(t-1));
        		--t;
        	}
        }
        System.out.println(val);
        return val;
    }
    public static int c2n (char s) {
    	switch (s) {
    		case 'I' :return 1;
    		case 'V' :return 5;
    		case 'X' :return 10;
    		case 'L' :return 50;
    		case 'C' :return 100;
    		case 'D' :return 500;
    		case 'M' :return 1000;
    		default :return 0;
    	}
    }
    
    public static void test () {
    	String s = "DCXXI";
    	romanToInt(s);
    }
}
