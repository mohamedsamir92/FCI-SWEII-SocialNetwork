package com.FCI.SWE.Models;

import static com.FCI.SWE.Models.OfyService.ofy;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Page {
	String name;
	String type;
	String category;
	String ownerEmail;
	@Id
	long PageID;
	Timeline pTimeline;
	long timelineID;
	int fans;
	ArrayList<String> fansMails;

	public Page(String n, String t, String c, String o) {
		name = n;
		type = t;
		category = c;
		ownerEmail = o;
		pTimeline = new Timeline();
		fans = 0;
		fansMails = new ArrayList<>();
		timelineID = pTimeline.getID();
	}

	public void save() {
		ofy().save().entity(this);
	}

	public static Page SearchPageByName(String name) {
		return ofy().load().type(Page.class).filter("name",name).first().now();
	}

	public void addFan(String fmail) {
		this.fans++;
		this.fansMails.add(fmail);
	}

}
