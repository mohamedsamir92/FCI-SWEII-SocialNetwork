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

import com.FCI.SWE.Models.PostsModel;
import com.FCI.SWE.Models.UserEntity;

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
	@GET
	@Path("/writePost/{email}&{password}&{text}")
	public String writePost(@PathParam("email") String email,
			@PathParam("password") String password,
			@PathParam("text") String text) {

		JSONObject obj = new JSONObject();

		UserEntity u = UserEntity.getUserByEMail(email);
		if (u == null) {
			obj.put(status, fail);
		} else {
			PostsModel.writePost(email, text);
			obj.put(status,ok);
		}

		return obj.toString();
	}
    
}
