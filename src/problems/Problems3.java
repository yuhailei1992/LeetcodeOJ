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
    
    public static String intToRoman(int num) {//accepted
        if (num == 0) {
        	return "";
        }
        String buf = "";
        char roman[][] = {{'I', 'V'}, {'X', 'L'}, {'C', 'D'}, {'M', 'N'}};
        int scale = 1000;
        int i = 3;
        while (num != 0 && i >= 0) {
        	int temp = num / scale;
        	if (temp != 0) {
	        	if (temp <= 3) {
	        		for (int j = 0; j < temp; ++j) {
	        			buf += roman[i][0];
	        		}
	        	}
	        	else if (temp < 5) {
	        		buf += roman[i][0];
	        		buf += roman[i][1];
	        	}
	        	else if (temp <= 8) {
	        		buf += roman[i][1];
	        		for (int j = 5; j < temp; ++j) {
	        			buf += roman[i][0];
	        		}
	        	}
	        	else {
	        		buf += roman[i][0];
	        		buf += roman[i + 1][0];
	        	}
        	}
        	num %= scale;
        	scale /= 10;
        	--i;
        }
        System.out.println(buf);
        return buf;
    }
    
    public static int[] plusOne(int[] digits) {//this solution is good, but overflows.
        if (digits == null) return digits;
        long num = 0;
        for (int i = 0; i < digits.length; ++i) {
            num *= 10;
            num += digits[i];
        }
        num++;
        //decide if there is a carry
        long temp = num;
        for (int j = 0; j < digits.length; ++j) {
            temp /= 10;
        }
        //
        int newlen = digits.length;
        if (temp != 0) {
            newlen++;
        }
        int ret[] = new int[newlen];
        for (int j = newlen - 1; j >=0; --j) {
            ret[j] = (int)(num % 10);
            num /= 10;
        }
        for (int j = 0; j < ret.length; ++j) {
        	System.out.print(ret[j]);
        }
        return ret;
    }

    public static void test () {
    	int num[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 1};
    	plusOne(num);
    }
}
