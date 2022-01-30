package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController // RestController -Spring RestController annotation is used to create RESTful web services using Spring MVC. Spring RestController takes care of mapping request data to the defined request handler method. Once response body is generated from the handler method, it converts it to JSON or XML response
public class HelloWorldController {
@Autowired
private MessageSource messageSource ; 	
	// GET 
	// URI -/hello-world  
	
	// method -> should " return hello world "
//	@RequestMapping(method=RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world") // we can use getmapping or requestmapping for return helloworld
	public String helloworld() {
		return "Hello world " ;
	}
	//hello-world-bean by using the bean 
	@GetMapping(path = "/hello-world-bean")
	public  HelloWorldBean helloWorldbean() {
		return new HelloWorldBean("Hello world");

	}
	//hello-world/path-variable/in28minutes
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public  HelloWorldBean helloWorldPathVariable(@PathVariable String name ) {
		return new HelloWorldBean(String.format("Hello world, %s", name) );
	}
	@GetMapping(path = "/hello-world-internationalised") 
	public String helloworldInternationalised(
		//	@RequestHeader(name="Accept-Language", required=false) Locale locale 
			) {
		return messageSource
				.getMessage("good.morning.message",
						null,"Default message" ,
						// locale) ;
						LocaleContextHolder.getLocale());
	}
}
