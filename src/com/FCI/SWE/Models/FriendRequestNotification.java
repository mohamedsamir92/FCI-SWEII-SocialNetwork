package com.FCI.SWE.Models;


import java.util.ArrayList;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.*;

import static com.FCI.SWE.Models.OfyService.ofy;

@Entity
public class FriendRequestNotification extends Notification{
	@Index private String user_email;
	private String sender;
	@Id private Long friendRequestNotiID;
	
	public FriendRequestNotification(String s,String r){
		this.user_email = s;
		this.sender = r;
	}

	public void save(){
		ofy().save().entity(this);
	}

	public static ArrayList<FriendRequestNotification> getListOfNotifications(String email){
		ArrayList<FriendRequestNotification> ret;
    	ret = (ArrayList<FriendRequestNotification>)
    			ofy().load().type(FriendRequestNotification.class).
    			filter("user_email",email).list();
		return ret;
	}

}
