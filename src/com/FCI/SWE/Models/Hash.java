package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.ArrayList;

class Hash {
	
	public String nameHashtage = null;
	public ArrayList<String> posts = new ArrayList <String> ();
	
	public void save(){
		ofy().save().entity(this);
	}
	
}
