package com.FCI.SWE.Models;

import java.util.Calendar;
import java.util.Date;


public class Messages {
	String sender;
	String receiver;
	String text;
	Date sendDate;
	
	Messages(){}
	
	Messages(String s,String r,String t){
		sender = s;
		receiver = r;
		text = t;
		sendDate = new Date();
		
	}
	
	

	
}
