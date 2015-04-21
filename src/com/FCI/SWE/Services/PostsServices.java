package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.Chat;
import com.FCI.SWE.Models.Friends;
import com.FCI.SWE.Models.PostsModel;
import com.FCI.SWE.Models.UserEntity;

import static com.FCI.SWE.Models.OfyService.ofy;

/**
 * This class contains REST services for posts, also contains action function for web
 * application
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 * edited by esraa khaled
 */
@Path("/posts/")
@Produces(MediaType.TEXT_PLAIN)
public class PostsServices{
	
	static private String status = "Status";
	static private String ok = "OK";
	static private String fail = "Failed";

	
	/*
	 * @author Fahmy
	 * 
	 * @Date 21-3
	 */
	/**
	 * service allow a user to write a post 
	 * @param email
	 * @param password
	 * @param text
	 * @return ok if user exists fail otherwise
	 */
	@POST
	@Path("/writePost/")

	public String writePost(@FormParam("sender") String sender,
			@FormParam("receiver") String receiver,
			@FormParam("text") String text) {
		
		JSONObject obj = new JSONObject();
		if(!Friends.areFriends(sender, receiver)){
			obj.put(status , fail);
		}else{
			new PostsModel(receiver,text).save();
			obj.put(status,ok);
		}
		return obj.toString();
	}
	@POST
	@Path("/userPost/")
	public String userPost(@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("text") String text,@FormParam("feeling") String feeling,@FormParam("privacy")String privacy) {
		JSONObject obj = new JSONObject();
		UserEntity u = UserEntity.getUserByEMail(email);
		ArrayList<UserEntity> allowedUsers =null;
		if(privacy=="Friends"){
			//loop all friends of user and add post to the
		    allowedUsers = u.getUserFreinds();
			for(int i=0;i<allowedUsers.size();i++){
				allowedUsers.get(i).SetAllowedPost(new PostsModel(email,text));
			}
		}
		if (u == null) {
			obj.put(status, fail);
		} else {
			new PostsModel(email,text,feeling).save();
			obj.put(status,ok);
		}
		return (String) obj.put(status,ok);
	}
	
    
}
