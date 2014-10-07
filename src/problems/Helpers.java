package problems;

import java.util.List;

public class Helpers {
    public static void showArray (int[] n) {
        for (int i = 0; i < n.length; ++i) {
            System.out.print(n[i] + ",");
        }
        System.out.println("end");
    }

    public static void showArray2 (int[][] n) {
        for (int j = 0; j < n.length; ++j) {
            for (int i = 0; i < n[0].length; ++i) {
                System.out.print(n[j][i] + ",");
            }
            System.out.println();
        }
        System.out.println("end");
    }

    public static void showList (List<Integer> l) {
        int len = l.size();
        System.out.println("size is " + len);
        for (int i = 0; i < len; ++i) {
            System.out.print(l.get(i));
        }
        System.out.println();
    }
}
