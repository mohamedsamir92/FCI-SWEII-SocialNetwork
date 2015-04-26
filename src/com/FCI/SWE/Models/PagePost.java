package com.FCI.SWE.Models;

public class PagePost extends PostsModel {
	
	long ownerId;
	public PagePost() {}

	public PagePost(long o,String t) {
		super(t);
		ownerId=o;
	}
	
	public String view() {
		String s="";
		s+=Page.SearchPageByID(ownerId);
		s+='\n';
		s+=text;
		return s;
	}
}
