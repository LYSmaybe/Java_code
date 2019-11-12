 public class MyTime {
	private int hour;
	private int minute;
	private int second;
	
	public MyTime(int hour, int minute, int second) {
		if (hour < 0 || hour > 23) {
			System.out.println("不支持的小时数");
			return;
		}
		
		if (minute < 0 || minute > 59) {
			System.out.println("不支持的分数");
			return;
		}
		
		if (second < 0 || second > 59) {
			System.out.println("不支持的秒数");
			return;
		}
		
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	// 在当前对象上添加多少秒
	public void add(int seconds) {
		second += seconds;
		
		while (second > 59) {
			second -= 60;
			minute ++;
		}
		while (minute > 59) {
			minute -= 60;
			hour ++;
		}
		while (hour > 23) {
			hour -= 24;
		}
		}
	
	public static void main(String[] args){
		MyTime time = new MyTime(00, 00, 00);
		System.out.println(time);
		time.add(58);
		System.out.println(time);
	}
	}
	







