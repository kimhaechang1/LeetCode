class Solution {
    public int myAtoi(String s) {
        // 32 비트 숫자로 전환하는 myAtoi를 구현하라
        // 공백: 어떠한 공백도 무시하라
        // 부호: 다음 문자가 -, +라면 
        // 따라오는 0을 스킵하면서 읽어라
        // 반올림: 만약 32비트 정수범위를 벗어난다면 그 값으로 처리해라

        // 문자열 길이가 0이면 리턴 0
        if (s.length() == 0) return 0;
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        // step 1: 공백이 안나올때 까지 밀기
        for(; start < c.length ; start++ ) {
            if (isDigit(c[start]) || isSigned(c[start]) || isAlpha(c[start])) break;
        }
        // step 2: 부호 결정 짓기
        char sign = '+';
        if (start < c.length && isSigned(c[start])) {
            if (c[start] == '-') sign = '-';
            start++;
        }
        // step 3: 따라오는 0은 무시하고 숫자를 안만날때까지 혹은 끝까지 정수를 읽어들이라
        ArrayList<Integer> tempList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(; start < c.length; start++) {
            if (c[start] - '0' >= 0 && c[start] - '0' <= 9) {
                tempList.add(c[start] - '0');
            } else {
                break;
            }
        }
        
        int index = 0;
        for(;index<tempList.size();index++) {
            if (tempList.get(index) >= 1 && tempList.get(index) <= 9) break;
        }
        for(;index<tempList.size();index++) {
            list.add(tempList.get(index));
        }

        // step 4: 오버플로 체크
        return convert(list, sign);
    }
    static boolean isDigit(char ch) {
        return ch - '0' >= 0 && ch - '0' <= 9;
    }
    static boolean isSigned(char ch) {
        return ch == '-' || ch == '+';
    }
    static boolean isAlpha(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '.');
    }
    static int convert(ArrayList<Integer> digitList, char sign) {
        if (digitList.size() == 0) return 0;
        char[] upper = String.valueOf(Integer.MAX_VALUE).toCharArray();
        char[] lower = String.valueOf(Integer.MIN_VALUE).toCharArray();
        StringBuilder sb = new StringBuilder();
        if (sign == '+') {
            if (digitList.size() > upper.length) return Integer.MAX_VALUE;
            if (digitList.size() == upper.length) {
                for(int i = 0;i<upper.length;i++) {
                    if (digitList.get(i) < upper[i] - '0') break;
                    else if (digitList.get(i) > upper[i] - '0') return Integer.MAX_VALUE;
                }
            }
        }
        if (sign == '-') {
            System.out.println(digitList);
            System.out.println(Arrays.toString(lower));
            if (digitList.size() > lower.length - 1) return Integer.MIN_VALUE;
            if (digitList.size() == lower.length - 1) {
                for(int i = 1;i<lower.length;i++) {
                    if (digitList.get(i - 1) < lower[i] - '0') break;
                    else if (digitList.get(i - 1) > lower[i] - '0') return Integer.MIN_VALUE;
                }
            }
        }
        if (sign == '-') sb.append(sign);
        for(int i = 0;i<digitList.size();i++) {
            sb.append(digitList.get(i));
        }
        return Integer.parseInt(sb.toString());
    }
}