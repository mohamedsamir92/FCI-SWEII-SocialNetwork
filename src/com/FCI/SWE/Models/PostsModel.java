package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.osgi.framework.hooks.service.FindHook;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.googlecode.objectify.ObjectifyService;

public class PostsModel{
	
	private String text;
	private String email;
	
	public PostsModel(String t,String e){
		text = t;
		email = e;
	}
	
	public String toString(){
		return String.format("%s %s" , email , text);
	}
	
	public static boolean writePost(String email,String text){
		
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        PostsModel post1 = new PostsModel(text,email);        
        PostsModel post2 = new PostsModel(text,email);        
        PostsModel post3 = new PostsModel(text,email);        
        List<PostsModel> arr = new  ArrayList<PostsModel>();
        arr.add(post1);arr.add(post2);arr.add(post3);

        Entity post = new Entity("posts" , email);
        post.setUnindexedProperty("posts", arr);
        datastore.put(post);
        
        Key key = KeyFactory.createKey("posts", email);
        Filter filter
                = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, key);
        Query q = new Query("posts").setFilter(filter);

        PreparedQuery res = datastore.prepare(q);
		Entity e = res.asSingleEntity();
		
		ArrayList<PostsModel> ret = (ArrayList<PostsModel>)e.getProperty("posts");
		
		for(PostsModel m: ret){
			System.out.println(m.toString());
		}
		
		
		return true;
	}
    
}