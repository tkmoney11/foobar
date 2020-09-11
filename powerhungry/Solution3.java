package powerhungry;
import java.util.Arrays;
public class Solution3 {
    public static String solution(int[] xs) {
        if (xs.length == 1) {
            return "" + xs[0];
        }
        // Your code here
        Arrays.sort(xs);
        String result = xs[0] < 0 && xs[1] < 0 ? "" + xs[0] : "0";
        int negativeCount = xs[0] < 0 && xs[1] < 0 ? 1 : 0;
        for (int i = 1; i < xs.length; i++) {
            if (result.equals("0")) {
                result = "" + xs[i];
            } else {
                if (xs[i] != 0) {
                    if (xs[i] < 0 && (negativeCount % 2 == 1 || (i < xs.length - 1 && xs[i+1] < 0))) {
                            negativeCount++;
                            result = multiply(result, "" + xs[i]);
                        } else if (xs[i] > 0) {
                            result = multiply(result, "" + xs[i]);
                        }
                    }
            }
        }
        
        return result;                
    }
    
    private static String add(String num1, String num2) {
        int num1C = num1.length() - 1;
        int num2C = num2.length() - 1;
        int leftOver = 0;
        String result = "";
        while (num1C >= 0 && num2C >= 0) {
            int n1 = Integer.parseInt(num1.substring(num1C,num1C+1));
            int n2 = Integer.parseInt(num2.substring(num2C,num2C+1));
            int n3 = n2 + n1 + leftOver;
            leftOver = n3 / 10;
            result = "" + (n3 % 10) + result;
            num1C--;
            num2C--;
        }

        while (num1C >= 0) {
            int n1 = Integer.parseInt(num1.substring(num1C,num1C+1));
            int n3 = n1 + leftOver;
            leftOver = n3 / 10;
            result = "" + (n3 % 10) + result;
            num1C--;
        } 

        while (num2C >= 0) {
            int n2 = Integer.parseInt(num2.substring(num2C,num2C+1));
            int n3 = n2 + leftOver;
            leftOver = n3 / 10;
            result = "" + (n3 % 10) + result;
            num2C--;

        }
        if (leftOver > 0) result = "" + leftOver + result;

        return result;   
    }
    
    private static String multiply(String num1, String num2) {
        boolean isNegative = false;
        if (num1.contains("-")) {
            num1 = num1.replace("-","");
            isNegative = !isNegative;
        }

        if (num2.contains("-")) {
            num2 = num2.replace("-", "");
            isNegative = !isNegative;
        }

        String sum = "0";
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n1 = Integer.parseInt(num1.substring(i,i+1));
                int n2 = Integer.parseInt(num2.substring(j,j+1));
                String n3 = "" + (n1 * n2);
                for (int k = 0; k < num1.length() + num2.length() - i - j - 2; k++) n3 += "0";
                // System.out.println("sum: " + sum);
                // System.out.println("n3: " + n3);
                sum = add(sum, n3);
            }
        }

        if (isNegative) sum = "-" + sum;
        return sum;
    }
}