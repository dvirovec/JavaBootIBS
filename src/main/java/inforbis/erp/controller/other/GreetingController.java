package inforbis.erp.controller.other;

import inforbis.erp.model.other.Greeting;
import inforbis.erp.service.other.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;
	
	@RequestMapping(value= "/erp/greetings", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Greeting>> getGreetings() {
	
			Iterable<Greeting> greetings = greetingService.findAll();
			
			return new ResponseEntity<Iterable<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/greetings/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {

		Greeting greeting = greetingService.findOne(id);


		if(greeting == null){
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/greetings", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {

		Greeting savedGreeting =  greetingService.create(greeting);

		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/greetings/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {

		Greeting updatedGreeting = greetingService.update(greeting);

		if(updatedGreeting==null){
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/greetings/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting){

		greetingService.delete(id);

		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);

	}


}
