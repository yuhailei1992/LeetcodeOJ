package problems;

public class Problems {
	public static void P1 () {
		System.out.println("Hello world");
	}
	String s = "the sky   is blue";
	public static String reverseWords() {
		String s = "    the sky is blue";
        s = s.trim();
        System.out.println(s);
        String buf = "";
        String[] splited = s.split("\\s+");
        int len = splited.length;
        System.out.println(len);
        for (int i = splited.length - 1; i >= 0; --i) {
        	buf += splited[i];
        	if (i != 0) {
        		buf += " ";
        	}
        	System.out.println(splited[i]);
        	System.out.println(splited[i].length());
        }
        System.out.println(buf);
        return s;
    }
}
