public class BinarySearch {
	public static int binarySearch(int[] a, int value){
		int left = 0;
		int right = a.length-1;
		while(left<=right){
			int mid = (left + right)/2;
			if(a[mid]==value){
				return mid;
		    }else if(a[mid]<value){
				left = mid + 1;
		    }else{
				right = mid -1;
			}
		}
		return -1;
	}
	public static void main (String[] args){
		int[] a = {1,2,3,4,5,6,7,8,9};
		int ret = binarySearch(a,5);
		System.out.println(ret);
	}
}