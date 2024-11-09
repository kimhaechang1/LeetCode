class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> cnt = new HashMap<>();
        for(char ap: s.toCharArray()) cnt.put(ap, cnt.getOrDefault(ap, 0) + 1);
        for(char ap: t.toCharArray()) {
            if (cnt.getOrDefault(ap, 0) - 1 < 0) {
                return false;
            }
            cnt.put(ap, cnt.getOrDefault(ap, 0) - 1);
        }
        return true;
    }
}