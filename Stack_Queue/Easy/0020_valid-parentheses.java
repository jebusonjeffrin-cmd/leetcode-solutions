class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        if(s.length() <= 1)return false;
        char first = s.charAt(0);
        if(first == ')' || first == ']' || first == '}')return false;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }
            else{
                if(stack.isEmpty())return false;
                char top = stack.peek();
                if(top == '[' && s.charAt(i) == ']'){
                    stack.pop();
                }
                else if(top == '{' && s.charAt(i) == '}'){
                    stack.pop();
                }
                else if(top == '(' && s.charAt(i) == ')'){
                    stack.pop();
                }
                else return false;
            }
        }
        return stack.isEmpty();
    }
}