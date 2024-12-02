class Solution {
    public int calculate(String s) {
        // 문자들을 하나하나씩 확인함으로서 순회하는 방법이 있다.
        // 1. 숫자의 경우 현재 number의 하나의 숫자일것이다.
        // 2. '+' 기호를 만난경우 이전까지 구성한 number를 결과에 더하고 새로운 number를 구성하기 시작한다.
        // 3. '-' 기호를 만난경우에도 위의 경우와 동일하다.
        // 4. '(' 이전까지의 결과를 stack에 괄호 결과에 쓰일 부호와 함께 push한다. 그러고 새로운 가공된 결과를 누적해야 하기에 result를 0으로 세팅한다.
        // 5. ')' 스택에서 두 숫자를 꺼낸다. 왜냐하면 중간저장한 결과를 다시 누적해야 하기 때문이다.

        // 중요한것은 괄호를 만난경우의 대처인데, 괄호 내부의 결과가 우선되어야 하므로 이전까지의 순차적 누적을 잠깐 다른곳에 저장하는것이다.
        // tip: 숫자를 10진수로 변환할 시, 뒤에 오는 수가 반드시 작은 자릿수 의미를 가진다면 앞까지의 누적된 수 * 10 + 현재수를 하면 된다.
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0;i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isDigit(ch)) {
                number = ((number * 10) + (ch - '0'));
                continue;
            }
            if (ch == '+') {
                result += (sign * number);
                sign = 1;
                number = 0;
                continue;
            }
            if (ch == '-') {
                result += (sign * number);
                sign = -1;
                number = 0;
                continue;
            }
            if (ch == '(') {
                stack.push(sign);
                stack.push(result);
                result = 0;
                sign = 1;
                number = 0;
                continue;
            }
            if (ch == ')') {
                result += (number * sign);
                number = stack.pop();
                sign = stack.pop();
                result *= sign;
                result += number;
                sign = 1;
                number = 0;
            }
            
        }
        // 마지막에 괄호가 없는 연산이어서 number가 남을 수 있음
        return result + (number * sign);
    }
    static boolean isDigit(char ch) {
        return ch - '0' >= 0 && ch - '0' <= 9;
    }

    
}