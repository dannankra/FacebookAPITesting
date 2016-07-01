package com.terseinc.graph.endpoints;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.terseinc.graph.Facebook;
import com.terseinc.graph.TokenManager;

public class User {
	private Client client;
	public User(){
		 client = ClientBuilder.newClient();
	}
	public Response getName(){
	    
    	Response response = getResponseFromEndPoint("me", "id, name", TokenManager.get_about_me_Token());
    	return response;
	}
	
	public Response getResponseFromEndPoint(String endpt, String qry, String token){
		   
    	Response response = client.target(Facebook.getBaseUrl())
				  .path(endpt)
				  .queryParam("fields", qry)
		    	  .queryParam("access_token", token)
		    	  .request(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML)
		    	  .get();
    	return response;
	}
}
