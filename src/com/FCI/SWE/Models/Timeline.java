package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Timeline {
	@Id Long id;	
//	ArrayList<Post> allPosts;
	public Timeline getTimelineByID(Long id){
		return ofy().load().type(Timeline.class).
				filter("id",id).first().now();	
	}
}