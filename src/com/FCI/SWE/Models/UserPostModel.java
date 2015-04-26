package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.FCI.SWE.Services.UserPost;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class UserPostModel extends PostsModel{
	@Id Long id;
	@Index String sender;
	private String receiver;
	private String feeling;
	private String privacy;
	List<Long > posts;
	
	public Long save(){
		ofy().save().entity(this);
		return id;
	}
	public Long getID(){
		return id;
	}
	public void addPost(Long obj){
		posts.add(obj);
	}
	public UserPostModel(){}
	
	public UserPostModel(String sender,String text,String feeling,String privacy) {
		super(text);
		this.sender=sender;
		this.feeling=feeling;
		this.privacy=privacy;
	}
	public UserPostModel(String sender,String receiver,String text) {
		super(text);
		this.sender = sender;
		this.receiver = receiver;
	}
	public static Long userPost(String email,String text,String feeling,String privacy){
		UserPostModel obj = new UserPostModel();
		Long i = new UserPostModel(email, text, feeling, privacy).save();
		obj.addPost(i);
		return i;
	}
	public static Long friendPost(String sender,String receiver,String text) {
		UserPostModel obj = new UserPostModel();
		Long i = new UserPostModel(sender, receiver, text).save();
		obj.addPost(i);	
		return i;
	}

	
}
