package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Chat {
	@Id Long id;
	@Index String user_one;
	@Index String user_two;
	List<Messages> messages;
	
	public Chat(){}
	
	public Chat(String u1,String u2){
		user_one = u1;
		user_two = u2;
		messages = new ArrayList<Messages>();
	}
	
	public List<Messages> getMessages(){return messages;}
	
	public void addMessage(Messages obj){
		messages.add(obj);
	}
	
	public void save(){
		ofy().save().entity(this);
	}
	
	public static Chat find(String u1,String u2){
		Chat obj = ofy().load().type(Chat.class).
					filter("user_one",u1).filter("user_two",u2).first().now();
		if(obj != null)return obj;
		
		obj = ofy().load().type(Chat.class).
				filter("user_two",u1).filter("user_one",u2).first().now();
		if(obj != null)return obj;
		
		return null;
	}
	
	
	public static void send(String u1,String u2,String text){
		Chat obj = find(u1,u2);
		if(obj == null)obj = new Chat(u1,u2);
		obj.addMessage(new Messages(u1,u2,text));
		obj.save();
		new MessageNotification(u2,u1,text).save();
	}
}
