package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.FCI.SWE.Services.UserPost;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserPostModel extends PostsModel{
	@Id    private Long id;
	@Index private String ownerEMail;
	@Index private Long timelineId;
	       private String feeling;
	       private String privacy;
	
   	public UserPostModel(){}
	
	public UserPostModel(String ownerEMail,String text,Long timelineId) {
		super(text);
		this.ownerEMail = ownerEMail;
		this.timelineId = timelineId;
		this.privacy = "public";
	}
	
	public void setFeeling(String s){
		feeling = s;
	}
	public void setPrivacy(String p){
		privacy = p;
	}
	public Long getTimelineId(){
		return timelineId;
	}
	public String getOwnerEMail(){
		return ownerEMail;
	}
	public Long getID(){
		return id;
	}
	public Long save(){
		ofy().save().entity(this);
		return id;
	}
	
}
