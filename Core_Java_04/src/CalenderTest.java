import java.time.*;

//打印当月日历表并标出当天
public class CalenderTest {
    public static  void main(String[] args){
        //构造一个对象，初始化为当前时间
        LocalDate date = LocalDate.now();
        //获得当前月、日的数值
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        //将date更改为这个月的第一天
        date = date.minusDays(today - 1);
        DayOfWeek weekday = date.getDayOfWeek();//当月第一天是周几，并转为int型
        int value = weekday.getValue();
        //制作出日历的title
        System.out.println(" Mon Tue Wed Thu Fri Sat Sun");
        for(int i = 1; i < value; i++){
            System.out.print("    ");
        }
        //只要日期在当月，就打印
        while(date.getMonthValue() == month){
            System.out.printf("%3d", date.getDayOfMonth());
            //打印期间，遇到当天，再标注出*
            if(date.getDayOfMonth() == today){
                System.out.print("*");
            }else{
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if(date.getDayOfWeek().getValue() == 1)
                System.out.println();//换行
        }
    }
}
