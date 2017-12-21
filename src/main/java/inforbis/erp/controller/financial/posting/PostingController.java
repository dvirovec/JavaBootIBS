package inforbis.erp.controller.financial.posting;


import inforbis.erp.model.financial.posting.Posting;
import inforbis.erp.service.financial.posting.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostingController {

	@Autowired
	private PostingService postingService;
	
	@RequestMapping(value= "/erp/posting", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Posting>> getPostings() {
	
			Iterable<Posting> postings = postingService.findAll();
			
			return new ResponseEntity<Iterable<Posting>>(postings, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/posting/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Posting> getPosting(@PathVariable("id") Long id) {

		Posting posting = null;

		if(id==-1) {
			posting = new Posting();
		}
		else {
			posting = postingService.findOne(id);
			if (posting == null) {
				return new ResponseEntity<Posting>(HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<Posting>(posting, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/posting", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Posting> createPosting(@RequestBody Posting posting) {

		Posting savedPosting =  postingService.create(posting);

		return new ResponseEntity<Posting>(savedPosting, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/posting/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Posting> updatePosting(@RequestBody Posting posting) {

		Posting updatedPosting = postingService.update(posting);

		if(updatedPosting==null){
			return new ResponseEntity<Posting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Posting>(updatedPosting, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/posting/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Posting> deletePosting(@PathVariable("id") Long id, @RequestBody Posting posting){

		postingService.delete(id);

		return new ResponseEntity<Posting>(HttpStatus.NO_CONTENT);

	}
}
