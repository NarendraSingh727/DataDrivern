import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Rough {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		String text="Tata Motors Ltd.  (-73.64%)";
		String temp[] = text.split("\\(");
		System.out.println(temp[0].trim());
		System.out.println(temp[1].split("\\)")[0].split("%")[0]);
		*/
		
		Date currentDate = new Date();
		
		String date="8/4/2013";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateToBeSelected =null;
		try {
			 dateToBeSelected = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(currentDate);
		System.out.println(dateToBeSelected);//
		String month=new SimpleDateFormat("MMMM").format(dateToBeSelected);		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dateToBeSelected);
	    int year = cal.get(Calendar.YEAR);
	    System.out.println(month +" "+year);
		System.out.println(currentDate.after(dateToBeSelected));
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
		
		/*
		  String month_yearExpected = month+" "+year;
	    
		while(true){
			
			String month_yearDisplayed = getText("monthAndYearText_xpath");
			if(month_yearDisplayed.equals(month_yearExpected))
				break; // correct month
			
			if(currentDate.after(dateToBeSelected))
				click("calBack_xpath");
			else
				click("calFront_xpath");
		}
		
		driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
		 */
	}

}
