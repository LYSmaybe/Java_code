import java.util.Scanner;

public class Age {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入你的年龄：");
		int i = scan.nextInt();
		if(i>0 && i<18){
			System.out.println("你是少年");
		}else if(i>18 && i<28){
			System.out.println("你是青年");
		}else if(i>28 && i<55){
			System.out.println("你是中年");
		}else{
			System.out.println("你是老年");
		}
	}
}