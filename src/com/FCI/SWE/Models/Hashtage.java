package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import com.FCI.SWE.Models.*;

import java.io.*;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class Hashtage {
	
	public PostsModel post = new PostsModel();
	public static ArrayList <Hash> H = new ArrayList<Hash> ();
	public Hash ha = new Hash ();
	
	public String textt = post.text;
	public String hash = null;
	
	public int startIndex = 0;
	public int endIndex = 0;
	
	@POST
	@Path("/writeHashtage/")
	
	public void hashPost (){
		 startIndex = textt.indexOf('#');{
			
			if (startIndex != -1){
				endIndex = textt.indexOf(' ');{
					hash = textt.substring(startIndex +1, endIndex);
				}
				ha.nameHashtage = hash;
				ha.posts.add(textt);
				H.add(ha);				
			}
		}
	}

	public static ArrayList<Hash> getList(String text1){
    	H.addAll(ofy().load().type(Hash.class).
		    			filter("Text",text1).list());
    	
    	for(Hash m: H){
    		m.save();
    	}
		return H;
	}
}






