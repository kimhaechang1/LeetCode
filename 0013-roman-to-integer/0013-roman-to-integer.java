class Solution {
    public int romanToInt(String s) {
        // 4 와 9를 만들기 위해서 IV, IX 를 사용한다.
        // 40과 90을 만들기 위해서 XL, XC를 사용한다.
        // 400 과 900을 만들기 위해서ㅓ CD, CM 을 사용한다.
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);map.put('V', 5);map.put('X', 10);map.put('L', 50);
        map.put('C', 100);map.put('I', 500);map.put('I', 1000);
        
        int index = 0;
        int answer = 0;
        for(;index < s.length();) {
            if () {
                answer -= map.get('I');

            }
        }
        return answer;
    }
    static boolean needSubstraction(int index, String s) {
        if (s.charAt(index) == 'V' && index - 1 > -1 && s.charAt(index - 1) == 'I') {
            return true;
        }
        if (s.charAt(index) == 'X' && index - 1 > -1 && s.charAt(index - 1) == 'I') {
            return true;
        }
        if (s.charAt(index) == 'L' && index - 1 > -1 && s.charAt(index - 1) == 'X') {
            return true;
        }
        if (s.charAt(index) == 'C' && index - 1 > -1 && s.charAt(index - 1) == 'X') {
            return true;
        }
        if (s.charAt(index) == 'C' && index - 1 > -1 && s.charAt(index - 1) == 'X') {
            return true;
        }
        if (s.charAt(index) == 'C' && index - 1 > -1 && s.charAt(index - 1) == 'X') {
            return true;
        }
    }
}