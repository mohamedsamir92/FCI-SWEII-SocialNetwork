package com.FCI.SWE.Models;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.*;
import static com.FCI.SWE.Models.OfyService.ofy;

@Entity
public class MessageNotification extends Notification{
	@Index private String user_email;
	@Id private Long chatID;
	
	public MessageNotification(String s,Long i){
		this.user_email = s;
		this.chatID = i;
	}

	public void save(){
		ofy().save().entity(this);
	}
	
}
