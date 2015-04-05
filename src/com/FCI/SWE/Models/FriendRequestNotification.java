package com.FCI.SWE.Models;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.*;

import static com.FCI.SWE.Models.OfyService.ofy;

public class FriendRequestNotification extends Notification{
	@Index private String user_email;
	@Id private Long friendRequestID;
	
	public FriendRequestNotification(String s,Long i){
		this.user_email = s;
		this.friendRequestID = i;
	}

	public void save(){
		ofy().save().entity(this);
	}

}
