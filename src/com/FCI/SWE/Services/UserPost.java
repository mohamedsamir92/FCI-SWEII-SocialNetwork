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
import com.FCI.SWE.Models.UserPostModel;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@Path("/post")
public class UserPost extends PostsModel {
	
	static private String status = "Status";
	static private String ok = "OK";
	static private String fail = "Failed";

	@POST
	@Path("/userPost/")
	public String userPost(@FormParam("email") String email,
			@FormParam("text") String text,@FormParam("feeling") String feeling,
			@FormParam("privacy")String privacy) throws JSONException {
		JSONObject obj = new JSONObject();
		UserPostModel.userPost(email, text, feeling, privacy);
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
				UserPostModel.friendPost(sender, receiver, text);
				obj.put(status, ok);
			}
		}
		return obj.toString();	
	}
	
    
}

