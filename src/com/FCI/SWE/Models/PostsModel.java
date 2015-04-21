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
public abstract class PostsModel{
	@Id protected Long id;
	protected String text;
	protected String felling;
	protected int privacy;
	/**
	 * empty constructor 
	 */
    public PostsModel(){}
    /**
     * constructor takes the owner and string text for a post
     * @param owner
     * @param t
     */
    public PostsModel(String t,int p){
    	text = t;
    	privacy =p;
    }
    public PostsModel(String t,String f ,int p){
    	text = t;
    	felling=f;
    	privacy =p;
    	
    }
   
    public void setPrivacy(int p){
    	privacy =p;
    }
    /**
     * this method save the post to the datastore
     */
    public void save(){
    	ofy().save().entity(this);
    }
    
}