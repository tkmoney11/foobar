package findtheaccesscodes;
import java.util.Arrays;

public class Solution5 {
    public static int solution(int[] l) {
        // Your code here
        // Arrays.sort(l); // TODO  ARE YOU JOKING?! (dont use this)
        int count = 0;
        int[] luckyDouble = new int[l.length];
        for (int i = 1; i < l.length - 1; i++) {
            for (int j = 0; j < i; j++){
                if (l[i] % l[j] == 0) luckyDouble[i]++;
            }
        }

        for (int i = 2; i < l.length; i++) {
            for (int j = 1; j < i; j++) {
                if (l[i] % l[j] == 0) count += luckyDouble[j];
            }
        }
        return count;
    }
}