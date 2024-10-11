package leetcode;

import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {

        String s = "IV";
        int sum = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) { // traverse the string
            int currentValue = map.get(s.charAt(i));
            if (i < s.length() - 1 && currentValue < map.get(s.charAt(i + 1))) { // check if character is not the last
                                                                                 // one, and if first char smaller than
                                                                                 // second char
                sum -= currentValue;
            } else {
                sum += currentValue;
            }
        }
        System.out.println(sum);

    }
}
