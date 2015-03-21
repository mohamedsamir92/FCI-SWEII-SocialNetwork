package com.FCI.SWE.Models;

import java.util.ArrayList;
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

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {

    private String name;
    private String email;
    private String password;

    /**
     * Constructor accepts user data
     *
     * @param name user name
     * @param email user email
     * @param password user provided password
     */
    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return password;
    }

    /**
     *
     * This static method will form UserEntity class using json format contains
     * user data
     *
     * @param json String in json format contains user data
     * @return Constructed user entity
     */
    public static UserEntity getUser(String json) {

        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(json);
            return new UserEntity(object.get("name").toString(), object.get(
                    "email").toString(), object.get("password").toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     *
     * This static method will form UserEntity class using user name and
     * password This method will serach for user in datastore
     *
     * @param name user name
     * @param pass user password
     * @return Constructed user entity
     */
    public static UserEntity getUser(String name, String pass) {
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Query gaeQuery = new Query("users");
        PreparedQuery pq = datastore.prepare(gaeQuery);
        for (Entity entity : pq.asIterable()) {
            System.out.println(entity.getProperty("name").toString());
            if (entity.getProperty("name").toString().equals(name)
                    && entity.getProperty("password").toString().equals(pass)) {
                UserEntity returnedUser = new UserEntity(entity.getProperty(
                        "name").toString(), entity.getProperty("email")
                        .toString(), entity.getProperty("password").toString());
                return returnedUser;
            }
        }
        return null;
    }

    public static UserEntity getUserByEMail(String email) {
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = KeyFactory.createKey("users", email);
        Filter filter
                = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, key);
        Query q = new Query("users").setFilter(filter);

        PreparedQuery res = datastore.prepare(q);
        Entity e = res.asSingleEntity();
        if (e == null) {
            return null;
        }
        UserEntity returnedUser = new UserEntity(e.getProperty(
                "name").toString(), e.getProperty("email")
                .toString(), e.getProperty("password").toString());
        return returnedUser;
    }

    public static UserEntity getUserByName(String name) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Filter filter = new FilterPredicate("name", FilterOperator.EQUAL, name);
        Query q = new Query("users").setFilter(filter);

        PreparedQuery res = datastore.prepare(q);
        Entity e = res.asSingleEntity();
        if (e == null) {
            return null;
        }
        UserEntity returnedUser = new UserEntity(e.getProperty(
                "name").toString(), e.getProperty("email")
                .toString(), e.getProperty("password").toString());
        return returnedUser;
    }

    /**
     * This method will be used to save user object in datastore
     *
     * @return boolean if user is saved correctly or not
     */
    public Boolean saveUser() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query gaeQuery = new Query("users");
        PreparedQuery pq = datastore.prepare(gaeQuery);
        List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

        Entity employee = new Entity("users", this.email);

        employee.setProperty("name", this.name);
        employee.setProperty("email", this.email);
        employee.setProperty("password", this.password);
        datastore.put(employee);

        return true;

    }

    public static boolean sendFriendRequest(String u1, String u2) {
        if (getUserByEMail(u1) == null || getUserByEMail(u2) == null) {
            return false;
        }
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity request = new Entity("friendRequests", u1 + "&" + u2);
        request.setProperty("sender", u1);
        request.setProperty("receiver", u2);
        datastore.put(request);
        return true;
    }

    public static boolean acceptFriendRequest(String u1, String u2) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Key key = KeyFactory.createKey("friendRequests", u1 + "&" + u2);
        Filter filter
                = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, key);
        Query q = new Query("friendRequests").setFilter(filter);
        PreparedQuery res = datastore.prepare(q);
        Entity e = res.asSingleEntity();

        if (e == null) {
            return false;
        }
        datastore.delete(e.getKey());

        Entity a = new Entity("friends", u1 + "&" + u2);
        a.setProperty("u1", u1);
        a.setProperty("u2", u2);
        datastore.put(a);

        return true;
    }

//	public static void search(String u1){
//		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//		Filter f = new FilterPredicate("u1",FilterOperator.EQUAL,u1);
//		Query q = new Query("friends").setFilter(f);
//		
//		PreparedQuery res = datastore.prepare(q);
//		
//		Entity e = res.asSingleEntity();
//		System.out.println(e.toString());
//
//	}
}
