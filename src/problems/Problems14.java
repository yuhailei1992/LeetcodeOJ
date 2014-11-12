package problems;

public class Problems14 {
	public int atoi(String str)//AC
	{
		if (str == null || str.length() == 0)
		{
			return 0;
		}
		str = str.trim();
		char[] str_arr = str.toCharArray();
		double val = 0;
		int i = 0;
		if (str_arr[0] == '+') ++i;
		else if (str_arr[0] == '-') ++i;
		for ( ; i < str_arr.length; ++i)
		{
			if (str_arr[i] < '0' || str_arr[i] > '9') break;
			val *= 10;
			val += (str_arr[i] - 48);
		}
		if (str_arr[0] == '-')
		{
			val = -val;
		}
		if (str_arr[0] != '-' && val + Integer.MAX_VALUE + 1 == 0)
			val = Integer.MAX_VALUE;
		return (int)val;
	}
	
	public void test () {
		System.out.println(atoi("2147483648"));
	}
}
