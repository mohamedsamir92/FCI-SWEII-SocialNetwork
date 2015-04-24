package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.UserEntity;

@Path("/pages/")
public class PageServices {
	private String status = "Status";
	private String ok = "OK";
	private String failed = "Failed";

	@POST
	@Path("/createPage/")
	public String writePost(@FormParam("email") String email,
			@FormParam("page name") String pName,
			@FormParam("page category") String pCateogry,
			@FormParam("page type") String pType) {

		JSONObject obj = new JSONObject();

		UserEntity u = UserEntity.getUserByEMail(email);
		if (u == null) {
			obj.put(status, failed);
		} else {
			new Page(pName, pType, pCateogry, email).save();
			obj.put(status, ok);
		}
		return obj.toString();
	}

	@POST
	@Path("/likePage")
	public String likePage(@FormParam("email") String email,
			@FormParam("page name") String pName) {
		JSONObject obj = new JSONObject();

		UserEntity u = UserEntity.getUserByEMail(email);
		Page p = Page.SearchPageByName(pName);
		if (u == null) {
			obj.put(status, failed);
		} else {
			if (p == null) {
				obj.put(status, failed);
			} else {
				p.addFan(email);
				obj.put(status, ok);
			}
		}
		return obj.toString();
	}

}
