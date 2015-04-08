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
    /**
     * empty constructor
     */
	public FriendRequest(){}
	/**
	 * this method assigns a friend request from a to b
	 * @param a
	 * @param b
	 */
	public FriendRequest(String a,String b){
		user_one = a;
		user_two = b;
	}
	
	/**
	 * getter for user 1
	 * @return user 1
	 */
	public String getUserOne(){return user_one;}
	/**
	 * getter for user 2
	 * @return user 2
	 */
	public String getUserTwo(){return user_two;}

	public static boolean find(String u1,String u2){
		if(ofy().load().type(FriendRequest.class).filter("user_one",u1).
				filter("user_two",u2).first().now() != null)return true;
		if(ofy().load().type(FriendRequest.class).filter("user_one",u2).
				filter("user_two",u1).first().now() != null)return true;
		return false;
	}
	
}
