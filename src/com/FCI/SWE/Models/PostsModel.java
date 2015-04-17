package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.osgi.framework.hooks.service.FindHook;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.*;

import static com.FCI.SWE.Models.OfyService.ofy;


@Entity
public class PostsModel{
	@Id Long id;
	@Index String owner_email;
	String text;
	
	/**
	 * empty constructor 
	 */
    public PostsModel(){}
    /**
     * constructor takes the owner and string text for a post
     * @param owner
     * @param t
     */
    public PostsModel(String owner,String t){
    	owner_email = owner;
    	text = t;
    }
    
    /**
     * this method save the post to the datastore
     */
    public void save(){
    	ofy().save().entity(this);
    }
    
}