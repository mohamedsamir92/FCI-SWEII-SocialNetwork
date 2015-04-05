package com.FCI.SWE.Models;

import java.util.ArrayList;

import org.apache.tools.ant.taskdefs.condition.Not;

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
	
	
	public static ArrayList<MessageNotification> getListOfNotifications(String email){
		ArrayList<MessageNotification> ret;
    	ret = (ArrayList<MessageNotification>)ofy().load().type(MessageNotification.class).
    			filter("user_email",email).list();
		
		return ret;
	}

	
}
