import java.util.Scanner;

public class PrimeNumber {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int flag = 1;
		for(int i=2;i<=(x/2);i++){
			if(x%i == 0){
				flag = 0;
				break;
			}
		}
		if(flag == 1){
			System.out.println(x + "是素数");
		}else{
			System.out.println(x + "不是素数");
		}
	}
}