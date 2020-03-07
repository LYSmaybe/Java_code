package 数组;

public class _66_加一 {
    public int[] plusOne(int[] digits) {
        //不需要扩充数组的情况
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i]++;
            digits[i] %= 10; //如果变成10需要的操作
            if(digits[i] != 0) return digits;
        }
        //出循环说明数组首位数字变成10，需要进位，扩充数组
        digits = new int[digits.length + 1];
        digits[0] = 1; //直接初始化后首位改为1，之后的数组一定都是0
        return digits;
    }
}
