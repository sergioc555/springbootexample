package com.websystique.springboot;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.websystique.springboot.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = SpringBootRestApiApp.class)
//@Transactional
public class TestCaseRest {

	@Autowired
	private TestRestTemplate restTemplate;
	
//	@Autowired
//	private AbstractConfigurableWebServerFactory webServerFactory;
//
//	@Test
//	public void testSsl() {
//		Assertions.assertTrue(this.webServerFactory.getSsl().isEnabled());
//	}

	@Test
	public void httpResponseOfGetAllUserIsOk() {
		ResponseEntity<String> httpResponse = this.restTemplate.getForEntity("/api/user/", String.class);
		Assertions.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
	}

	@Test
	public void httpResponseOfGetAllUserIsJson() {
		ResponseEntity<String> httpResponse = this.restTemplate.getForEntity("/api/user/", String.class);
		MediaType mtype = httpResponse.getHeaders().getContentType();
		Assertions.assertEquals("application", mtype.getType());
		Assertions.assertEquals("json", mtype.getSubtype());
	}

	/* GET */
	@Test
	public void userofGetAllUserIsNotNull() {
		System.out.println("Testing listAllUsers API-----------");
		boolean thereIsName = false;

		List<HashMap<String, Object>> usersMap = this.restTemplate.getForObject("/api/user/", List.class);

		if (usersMap != null) {
			for (HashMap<String, Object> map : usersMap) {
				System.out.println("User : id=" + map.get("id") + ", Name=" + map.get("name") + ", Age="
						+ map.get("age") + ", Salary=" + map.get("salary"));
				if (map.get("name").equals("Tom")) {
					thereIsName = true;
				}
			}
			Assertions.assertTrue(thereIsName);
		} else {
			Assertions.fail("not Users Fecthed");
		}
	}

	/* GET */
	@Test
	public void httpResponseOfGetUSerIsOk() {
		ResponseEntity<User> httpResponse = this.restTemplate.getForEntity("/api/user/2", User.class);
		Assertions.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());

		Assertions.assertEquals(40,httpResponse.getBody().getAge());
	}

	@Test
	public void httpResponseOfGetUSerIsJson() {
		ResponseEntity<String> httpResponse = this.restTemplate.getForEntity("/api/user/2", String.class);
		MediaType mtype = httpResponse.getHeaders().getContentType();
		Assertions.assertEquals("application", mtype.getType());
		Assertions.assertEquals("json", mtype.getSubtype());
	}

	/* POST */
	@Test
	public void httpResponseOfPostUserIsCreated() {
		System.out.println("Testing PostUser API----------");
		User userPost = new User(200, "Sarah", 51, 134.0);
		ResponseEntity<User> httpResponse = this.restTemplate.postForEntity("/api/user/", userPost, User.class);
		Assertions.assertEquals(HttpStatus.CREATED, httpResponse.getStatusCode());
		
		HashMap<String, Object> user = this.restTemplate.getForObject("/api/user/200", HashMap.class);
		Assertions.assertEquals(200, user.get("id"));
		Assertions.assertEquals("Sarah", user.get("name"));
		Assertions.assertEquals(51, user.get("age"));
		Assertions.assertEquals(134.0, user.get("salary"));
	}
	
//	@Test
//	public void getUser() {
//		System.out.println("Testing getUser API----------");
//		HashMap<String, Object> user = this.restTemplate.getForObject("/api/user/200", HashMap.class);
//		Assertions.assertEquals(200, user.get("id"));
//		Assertions.assertEquals("Sarah", user.get("name"));
//		Assertions.assertEquals(51, user.get("age"));
//		Assertions.assertEquals(134.0, user.get("salary"));
//	}

	/* PUT */
	@Test
	public void updateUser() {
		System.out.println("Testing update User API----------");
    	String url = "http://localhost:8080/SpringBootRestApi/api/user/1";
		User userPut = new User(1, "Samy", 30, 70001.0);
    	HttpEntity<Object> request = new HttpEntity<Object>(userPut); 
    	ResponseEntity<String> httpResponse = this.restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		Assertions.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
		
		HashMap<String, Object> user = this.restTemplate.getForObject("/api/user/1", HashMap.class);
		Assertions.assertEquals(1, user.get("id"));
		Assertions.assertEquals("Samy", user.get("name"));
		Assertions.assertEquals(30, user.get("age"));
		Assertions.assertEquals(70001.0, user.get("salary"));
	}

	/* DELETE */
    @Test
	public void deleteUser() {
    	String url = "http://localhost:8080/SpringBootRestApi/api/user/3";
        HttpEntity<String> request =null;
    	ResponseEntity<String> httpResponse = this.restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, httpResponse.getStatusCode());

		HashMap<String, Object> user = this.restTemplate.getForObject("/api/user/3", HashMap.class);
		ResponseEntity<String> httpResponseGet = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, httpResponseGet.getStatusCode());
		Assertions.assertEquals("{\"errorMessage\":\"User with id 3 not found\"}",httpResponseGet.getBody());
	}
//
//	/* DELETE */
//	public void deleteAllUsers() {
//		System.out.println("Testing all delete Users API----------");
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.delete(REST_SERVICE_URI + "/user/");
//	}
    
//    public static void main(String args[]){
//        listAllUsers();
//        getUser();
//        createUser();
//        listAllUsers();
//        updateUser();
//        listAllUsers();
//        deleteUser();
//        listAllUsers();
//        deleteAllUsers();
//        listAllUsers();
//    }
}