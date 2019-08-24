package inforbis.erp.controller.base;

import inforbis.erp.model.base.Country;
import inforbis.erp.service.base.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value= "/erp/country", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Country>> getCountrys() {
	
			Iterable<Country> countrys = countryService.findAll();
			
			return new ResponseEntity<Iterable<Country>>(countrys, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/country/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {

		if(id==-1)
			return new ResponseEntity<Country>(new Country(), HttpStatus.OK);

		Country country = countryService.findOne(id);

		if(country == null){
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Country>(country, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/country", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {

		Country savedCountry =  countryService.create(country);

		return new ResponseEntity<Country>(savedCountry, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/country/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Country> updateCountry(@RequestBody Country country) {

		Country updatedCountry = countryService.update(country);

		if(updatedCountry==null){
			return new ResponseEntity<Country>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Country>(updatedCountry, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/country/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") Long id, @RequestBody Country country){

		countryService.delete(id);

		return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);

	}
}
