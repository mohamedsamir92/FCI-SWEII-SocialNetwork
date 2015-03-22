package com.FCI.SWE.Models;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static com.FCI.SWE.Models.OfyService.ofy;


@Entity
public class Friends {
	@Id Long id;
	@Index String user_one ;
	@Index String user_two;

	
	public Friends(){}
	public Friends(String a,String b){
		user_one = a;
		user_two = b;
	}
	
	public String getUserOne(){return user_one;}
	public String getUserTwo(){return user_two;}

	public static boolean areFriends(String u1,String u2){
		Friends obj = ofy().load().type(Friends.class).filter("user_one",u1)
			.filter("user_two",u2).first().now();
		if(obj != null)return true;

		obj = ofy().load().type(Friends.class).filter("user_one",u2)
		.filter("user_two",u1).first().now();
		if(obj != null)return true;
		
		return false;
	}
	
	
	
	
}
