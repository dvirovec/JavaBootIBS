package inforbis.erp.controller.base;

import inforbis.erp.model.base.Town;
import inforbis.erp.service.base.town.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TownController {

	@Autowired
	private TownService townService;
	
	@RequestMapping(value= "/erp/town", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Town>> getTowns() {
	
			Iterable<Town> towns = townService.findAll();
			
			return new ResponseEntity<Iterable<Town>>(towns, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/town/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Town> getTown(@PathVariable("id") Long id) {

		Town town = townService.findOne(id);

		if(town == null){
			return new ResponseEntity<Town>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Town>(town, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/town/country/{country_code}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Town>> getTownsByCountyCode(@PathVariable("country_code") String countryCode) {

		Iterable<Town> towns = townService.findByCountryCode(countryCode);

		return new ResponseEntity<Iterable<Town>>(towns, HttpStatus.OK);
	}


	@RequestMapping(value= "/erp/town/name/{name}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Town>> getTownsStartWith(@PathVariable("name") String name) {

		Iterable<Town> towns = townService.findByName(name);

		return new ResponseEntity<Iterable<Town>>(towns, HttpStatus.OK);
	}



	@RequestMapping(value= "/erp/town", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Town> createTown(@RequestBody Town town) {

		Town savedTown =  townService.create(town);

		return new ResponseEntity<Town>(savedTown, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/town/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Town> updateTown(@RequestBody Town town) {

		Town updatedTown = townService.update(town);

		if(updatedTown==null){
			return new ResponseEntity<Town>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Town>(updatedTown, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/town/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Town> deleteTown(@PathVariable("id") Long id, @RequestBody Town town){

		townService.delete(id);

		return new ResponseEntity<Town>(HttpStatus.NO_CONTENT);

	}
}
