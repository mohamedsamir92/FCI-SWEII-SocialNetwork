package com.FCI.SWE.Services;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.ArrayList;

import javax.validation.constraints.Past;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.FCI.SWE.Models.Friends;
import com.FCI.SWE.Models.PostsModel;
import com.FCI.SWE.Models.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
@Path("/post")
public class UserPost extends PostsModel {
	static private String status = "Status";
	static private String ok = "OK";
	static private String fail = "Failed";
	
	private String sender;
	private String receiver;
	private String feeling;
	private String privacy;

	 
	public void save(){
			ofy().save().entity(this);
	}
	
	public UserPost(String sender,String text,String feeling,String privacy) {
		super(text);
		this.sender=sender;
		this.feeling=feeling;
		this.privacy=privacy;
	}
	public UserPost(String receiver,String text) {
		super(text);
		this.receiver=receiver;
	}
	@POST
	@Path("/userPost/")
	public String userPost(@FormParam("email") String email,
			@FormParam("text") String text,@FormParam("feeling") String feeling,
			@FormParam("privacy")String privacy) throws JSONException {
		JSONObject obj = new JSONObject();
		UserEntity u = UserEntity.getUserByEMail(email);
		
//		ArrayList<UserEntity> allowedUsers = new ArrayList<UserEntity>();
//		if(privacy=="Friends"){
//			//loop all friends of user and add post to the
//		    allowedUsers = u.getUserFreinds();
//			for(int i=0;i<allowedUsers.size();i++){
//				allowedUsers.get(i).SetAllowedPost(new PostsModel(email,text));
//			}
//		}
		if (u == null) {
			obj.put(status, fail);
		} else {
				new UserPost(email,text,feeling,privacy).save();
		}
		return  obj.put(status,ok).toString();
		
	}
	@POST
	@Path("/freindPost/")
	public String freindPost(@FormParam("sender") String sender,
			@FormParam("receiver") String receiver,
			@FormParam("text") String text) throws JSONException{

		JSONObject obj = new JSONObject();
		UserEntity u = UserEntity.getUserByEMail(receiver);
		if (u == null) {
			obj.put(status, fail);
		}else{
			if(!Friends.areFriends(sender, receiver)){
				obj.put(status , fail);
			}else{
				new UserPost(text,receiver).save();
				obj.put(status,ok);
			}
		}
		return obj.toString();
		
	}
	
    
}

