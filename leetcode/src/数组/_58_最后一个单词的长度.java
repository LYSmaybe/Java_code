package 数组;

public class _58_最后一个单词的长度 {
    //需要考虑结尾处有空格的情况
    public int lengthOfLastWord(String s) {
        int n = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) == ' '){
                if(n == 0) continue;
                break;
            }
            n++;
        }
        return n;
    }
}
