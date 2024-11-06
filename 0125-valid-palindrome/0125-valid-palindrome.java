import java.util.*;

class Solution {
    public boolean isPalindrome(String s) {
        char[] crr = s.toLowerCase().toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for(char c : crr) {
            if (c >= 97 && c <= 122) {
                list.add(c);
            } else if (c - '0' >= 0 && c - '0' <= 9) {
                list.add(c);
            }
        }
        
        for(int i = 0;i<list.size() / 2;i++) {
            if (list.get(i) != list.get(list.size() - 1 - i)) return false;
        }
        return true;
    }
}