package com.FCI.SWE.Models;

import com.FCI.SWE.Models.*;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;


public class OfyService {
	static {
		ObjectifyService.register(PostsModel.class);
		ObjectifyService.register(UserEntity.class);
		ObjectifyService.register(FriendRequest.class);
		ObjectifyService.register(Friends.class);
		ObjectifyService.register(FriendRequestNotification.class);
		ObjectifyService.register(MessageNotification.class);
	}
	
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
