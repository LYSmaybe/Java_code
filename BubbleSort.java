import java.util.Arrays;
//还有一些问题
//n个数字的数列，冒泡n-1次就有序了
public class BubbleSort {
	public static void swap(int[] a, int i, int j){
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void bubbleSort(int[] a){
		//需要冒泡的次数
		for(int i=0; i<a.length-1; i++){
			//排序的区间循环范围[0，长度-已有序的-1]
			for(int j=0;j<a.length-i-1; j++){
				if(a[j]>a[j+1]){
					swap(a,a[j],a[j+1]);
				}
			}
		}
	}
	
	public static void main(String[] args){
		int[] ar = {9,8,7,6,5,4,3,2,1,0};
		bubbleSort(ar);
		System.out.println(Arrays.toString(ar));
	}
}
	
	