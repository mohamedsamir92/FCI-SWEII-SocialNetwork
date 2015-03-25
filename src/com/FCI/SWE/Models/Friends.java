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
    private static Objectify ofy = OfyService.ofy();

	/**
	 * empty constructor
	 */
	public Friends(){}
	/**
	 * constructor takes 2 string a and b that both are friends
	 * @param a
	 * @param b
	 */
	public Friends(String a,String b){
		user_one = a;
		user_two = b;
	}
	
    /**
     * getter for user one
     * @return user one
     */
	public String getUserOne(){return user_one;}
	/**
     * getter for user two
     * @return user two
     */
	public String getUserTwo(){return user_two;}

}
