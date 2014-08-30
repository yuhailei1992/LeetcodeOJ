package problems;

import java.util.Stack;

public class Problems {
	public static void P1 () {//pass
		String s = "    the sky is blue";
        s = s.trim();//remove leading and trailing spaces
        System.out.println(s);
        String buf = "";//string buffer
        String[] strsplit = s.split("\\s+");
        for (int i = strsplit.length - 1; i >= 0; --i) {
        	buf += strsplit[i];
        	if (i != 0) {
        		buf += " ";
        	}
        	System.out.println(strsplit[i]);
        	System.out.println(strsplit[i].length());
        }
        System.out.println(buf);
    }
	
	public static void P2 () {//pass
		String[] tokens = {"3", "-4", "+"};
		int len = tokens.length;
		String[] stk = new String[len];
		String ops = "+-*/";		
		int stkptr = 0;
		for (int i = 0; i < len; ++i) {
			if (!ops.contains(tokens[i])) {
				stk[stkptr] = tokens[i];
				stkptr++;
			}
			else {
				int temp = 0;
				int index = ops.indexOf(tokens[i]);
				switch (index) {
					case 0: temp = Integer.parseInt(stk[stkptr - 2]) + Integer.parseInt(stk[stkptr - 1]);
						break;
					case 1: temp = Integer.parseInt(stk[stkptr - 2]) - Integer.parseInt(stk[stkptr - 1]);
						break;
					case 2: temp = Integer.parseInt(stk[stkptr - 2]) * Integer.parseInt(stk[stkptr - 1]);
						break;
					case 3: temp = Integer.parseInt(stk[stkptr - 2]) / Integer.parseInt(stk[stkptr - 1]);
						break;
					default :
						break;
				}
				stk[stkptr - 2] = Integer.toString(temp);
				stkptr--;
			}
		}
		System.out.println(stk[0]);
	}
	
	public static void P3 () {
		
	}
	
	public static void reverseInteger () {//pass
		int x = -321;
		int isNeg = 0;
		int x_new = 0;
		if (x < 0) {
			x = -x;
			isNeg = 1;
		}
		while (x != 0) {
			int m = x % 10;
			x_new = x_new * 10;
			x_new += m;
			x = x / 10;
		}
		if (isNeg == 1) {
			x_new = -x_new;
		}
		System.out.println(x_new);
	}
}
