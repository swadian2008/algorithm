package algorithm;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

/**
 * @author swadi
 * @date 2020/4/9
 * @Version 1.0
 */
public class Solution {
    public boolean isValid(String s) {
        // 使用我们自定义的栈实现，当然也可以替换为Java的Stack，结果是一样的
        ArrayStack<Character> stack = new ArrayStack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 如果栈为空-根据题来设置
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("()]{}"));
    }
}
