package com.FCI.SWE.Models;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static com.FCI.SWE.Models.OfyService.ofy;

@Entity
public class FriendRequest {
	@Id Long id;
	@Index String user_one ;
	@Index String user_two;
    private static Objectify ofy = OfyService.ofy();

	public FriendRequest(){}
	public FriendRequest(String a,String b){
		user_one = a;
		user_two = b;
	}
	
	public String getUserOne(){return user_one;}
	public String getUserTwo(){return user_two;}
	
	
}
