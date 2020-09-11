package ilovelancejanice;
public class Solution {
    public static String solution(String x) {
        // iterate through each char
        String result = "";
        for (int i = 0; i < x.length(); i++) {
            char currChar = x.charAt(i);
            if (currChar <= 'z' && currChar >= 'a') {
                currChar = (char) ('z' - currChar + 'a'); // rearrange currChar
            }
            result += currChar; // append currChar
        } 
        return result;
    }
}