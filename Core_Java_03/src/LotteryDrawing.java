/*
import java.util.Arrays;
import java.util.Scanner;

public class LotteryDrawing {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need to draw?");
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw?");
        int n = in.nextInt();

        //创建从1—n的数组
        int[] numbers = new int[n];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = i+1;
        }

        //创建新的数组，放入抽出的k个数字
        int[] result = new int[k];
        for(int i = 0; i < result.length; i++){
            //创造0—n-1的随机下标值
            int r = (int)(Math.random()*n);
            //挑选出随机下标对应的数字
            result[i] = numbers[r];
            //用数组中最后一个元素替换当前随机下标的值，即让选过的值被覆盖
            numbers[r] = numbers[n-1];
            n--;
        }
        //对选出的随机值进行排序
        Arrays.sort(result);
        System.out.println("Bet the following combination.I will make you rich!");

        for(int r : result) //打印result数组中的所有元素r
            System.out.println(r);
    }
}
*/