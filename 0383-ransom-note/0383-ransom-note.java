class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for(char m: magazine.toCharArray()) cnt[m - 'a']++;
        for(char ransom: ransomNote.toCharArray()) {
            if (cnt[ransom - 'a'] - 1 < 0) return false;
            cnt[ransom - 'a']--;
        }
        return true;
    }
}