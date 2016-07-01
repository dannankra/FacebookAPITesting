package com.terseinc.graph.sanity;

import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.terseinc.graph.endpoints.User;

public class Sanity {
	public User user;
	public JSONObject obj;
	final static Logger logger = Logger.getLogger(Sanity.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * @epic. FP-777 Facebook Graph API
	 * @requirement. REQ08 - Users must be able to see their names on facebook
	 * @resources. NONE
	 * @testcase. TC03 - Test that the current name of the User can be retrieved
	 * @scenarios. A user logs into facebook and wants to see his/her name on facebook
	 * @expectedresults. The full name of the user is displayed
	 * 
	 */
   
    @Test
    public void TestThatTheNameOfAUserCanBeRetrieved(){
     Response response = user.getName();
     String body = response.readEntity(String.class);
     Assert.assertEquals(200, response.getStatus());
     
     obj = new JSONObject(body);
     logger.info(obj);
     
     Assert.assertEquals("Terseinc Tester", obj.getString("name"));
    }
    
   
	/**
	 * @epic. FP-777 Facebook Graph API
	 * @requirement. REQ07 - Only the id and name of the user must be returned if a call
	 * 				 is made to the Me endpoint with no fields specified
	 * @resources. NONE
	 * @testcase. TC04 - Test That when No field is supplied for the Me endpoint the current user's 
     * name and Id is returned.
	 * @scenarios.
	 * @expectedresults. Only the id and name of the user is returned
	 * 
	 */
   
    @Test
    public void TestThatIfNoFieldsAreSpecifiedForTheMeEndPointTheIDAndNameOfCurrentUserIsReturned(){
    	 String endpoint = "me";
    	 String fieldQuery = "";
	     Response response = user.getResponseFromEndPoint(endpoint, fieldQuery);
	     String body = response.readEntity(String.class);
	     Assert.assertEquals(200, response.getStatus());
	     
	     
	     obj = new JSONObject(body);
	     logger.info(obj);
	     
	     Assert.assertEquals("Terseinc Tester", obj.getString("name"));
	     Assert.assertEquals("103119923391802", obj.getString("id"));
    }

}
