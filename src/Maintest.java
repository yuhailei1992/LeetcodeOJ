import problems.DynamicProgramming;
public class Maintest {
	
	public static void main(String[] args) {
		System.out.println("Begin test...");
		//Problems2.testArray();
		//int num[] = {2, 1, 5, 3, 6, 4, 8, 9, 7};
		int num[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		//int num[] = {4,2,5,7,6,4,8,2,1,4,6,10};
		DynamicProgramming.LISevenbetter(num);
		DynamicProgramming.LISbetter(num);
		DynamicProgramming.LIS(num);
	}
}
