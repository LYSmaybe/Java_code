package 栈;

import java.util.HashMap;
import java.util.Stack;

//哈希表 + 栈
public class _20_有效的括号 {
    private HashMap<Character, Character> map = new HashMap<>();
    //普通成员的成员初始化
    public _20_有效的括号(){
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();

        int n = s.length();
        //通过循环依次取出元素并处理
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            //如果取出的是左括号，即key
            if(map.containsKey(c)){
                stack.push(c);
            }else {
                if(stack.isEmpty()) return false; //右括号多余
                if(c != map.get(stack.pop())) return false; //此时出栈的key所拥有的value与c不匹配
            }
        }
        //循环完毕：刚好匹配完 || 栈内还有未匹配元素
        return stack.isEmpty();
    }
}
