package Experiment;

import java.util.Date;

public class Time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//method chaining
    Date date =new Date();
    System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
	}

}
