class Solution {
    public boolean backspaceCompare(String s, String t) {
        // s, t 라는 문자열이 주어진다.
        // 공백 문자 에디터에서 타이핑된 두 문자열이 같다면 true를 반환하라
        // #은 백스페이스 캐릭터를 의미한다.


        // 지우기 쪽에서 중요한 관찰로, 어짜피 자기 앞을 지운다는 생각보다는 그만큼 백스페이스 키보드를 눌러야한다는것을 기반으로 푼다.
        // 그러니 지워야하는 문자의 개수를 카운트한다.
        int sIdx = s.length() - 1;
        int tIdx = t.length() - 1;
        int back = 0;
        while(true) {
            back = 0;
            while(sIdx > -1 && (back > 0 || s.charAt(sIdx) == '#')) {
                // 지워야하는 개수가 남아있거나, #이 계속 나오는경우 (즉, 지우기 카운트를 더해야하는 경우)
                back += (s.charAt(sIdx) == '#' ? 1 : -1);
                sIdx--;
            }
            back = 0;
            while(tIdx > -1 && (back > 0 || t.charAt(tIdx) == '#')) {
                // 지워야하는 개수가 남아있거나, #이 계속 나오는경우 (즉, 지우기 카운트를 더해야하는 경우)
                back += (t.charAt(tIdx) == '#' ? 1 : -1);
                tIdx--;
            }
            if (sIdx > -1 && tIdx > -1 && s.charAt(sIdx) == t.charAt(tIdx)) {
                sIdx--;
                tIdx--;
                continue;
            }
            break;
        }
        // 둘 다 완전히 비워졌는지
        return sIdx < 0 && tIdx < 0;
    }
}