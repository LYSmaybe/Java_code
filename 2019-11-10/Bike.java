public class Bike{
	//属性（一般用private）
	private String color;
	private int speed;
	private int max_speed;
	
	//构造方法
	Bike(){
		this.color = color;
		speed = 0;
		max_speed = 100;
	}
	
	//方法
	void setColor(String color){
		this.color = color;
	}
	void speedUp(){
		speed += 10;
		if(speed > max_speed){
			speed = max_speed;
		}
	}
	void speedDown(){
		speed -= 10;
		if(speed < 0){
			speed = 0;
		}
	}
	//题目中要求写的返回值
	String getColor(){
		return color;
	}
	int getSpeed(){
		return speed;
	}
}