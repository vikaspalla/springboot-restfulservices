package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoServices service;

	// Get /users
	// retrieve all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// Get /users/{id}
	// retrieveUse(int id )
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id-"+id) ;
		EntityModel<User> model = EntityModel.of(user) ;  
	
	WebMvcLinkBuilder linkToUsers = 
			linkTo(methodOn(this.getClass()).retrieveAllUsers());
	model.add(linkToUsers.withRel("all-users")) ;
	return model ; 
	}
	

	// CREATED
	// input - details of user
	// output - Created and Return the created Uri
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/users/{id}")
	public void createUser1(@RequestBody User user) {
		User savedUser = service.save(user);

	}

//	@PostMapping("/users")
//	public ResponseEntity<Object> createUser2(@RequestBody User user) {
//		User savedUser = service.save(user);
//	//Created 
//		///user/{id} savedUser.getId()
//		URI location = ServletUriComponentsBuilder.
//		fromCurrentRequest().path("/{id}").
//		buildAndExpand(savedUser.getId()).toUri();
//		return ResponseEntity.created(location).build();
//	@GetMapping("/users/{id}")
//	public User retrieveUser1(@PathVariable int id) {
//		User user = service.findOne(id);
//		if(user==null)
//			throw new UserNotFoundException("id-"+id) ;
//		return user ;

//	}
}
