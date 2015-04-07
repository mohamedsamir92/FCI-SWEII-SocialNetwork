package com.FCI.SWE.Models;

import java.util.ArrayList;

import org.apache.tools.ant.taskdefs.condition.Not;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.*;

import static com.FCI.SWE.Models.OfyService.ofy;

@Entity
public class MessageNotification extends Notification{
	@Id Long id;
	@Index private String user_email;
	private String sender_email;
	private String text;
	private Boolean read;
	
	public MessageNotification(){}
	
	public MessageNotification(String r,String s,String t){
		this.user_email = r;
		this.sender_email = s;
		this.text = t;
		this.read = false;
	}

	public void save(){
		ofy().save().entity(this);
	}
	
	
	public static ArrayList<MessageNotification> getListOfNotifications(String email){
		ArrayList<MessageNotification> ret = new ArrayList<MessageNotification>();
    	ret.addAll(ofy().load().type(MessageNotification.class).
    			filter("user_email",email).list());
    	for(MessageNotification m: ret){
    		m.read = true;
    		m.save();
    	}
		return ret;
	}
	public String getSender(){
		return this.sender_email;
	}
	public String getText(){
		return this.text;
	}
	
}
