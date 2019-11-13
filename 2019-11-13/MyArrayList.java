import java.util.Arrays;

public class MyArrayList{
	private int[] array;
	private int size;
	
	public MyArrayList(){
		array = new int[10];
		size = 0;
	}
	
	public void pushFront(int element){
		//i代表空心，赋值[空心]=[实心]
		for(int i = size; i >= 1; i--){
			array[i] = array[i - 1];
		}
		array[0] = element;
		size ++;
	}
	
	public void insert(int element, int index){
		//i代表实心，赋值[空心]=[实心](i要整齐)
		for(int i = size-1; i >=index; i--){
			array[i+1] = array[i];
		}
		array[index] = element;
		size++;
	}
	
	@Override
	public String toString(){
		return Arrays.toString(Arrays.copyOf(array,size));
	}
	
	public static void main(String args[]){
		MyArrayList list = new MyArrayList();
		list.pushFront(1);
		list.pushFront(9);
		list.pushFront(3);
		list.pushFront(9);
		System.out.println(list);
		list.insert(5,2);
		list.insert(0,3);//935091
		System.out.println(list);
	}
}