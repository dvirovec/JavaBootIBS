package inforbis.erp.controller.financial.period;

import inforbis.erp.model.financial.period.AccountPeriod;
import inforbis.erp.service.financial.period.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PeriodController {

	@Autowired
	private PeriodService periodService;
	
	@RequestMapping(value= "/erp/account_period", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<AccountPeriod>> getPeriods() {
	
			Iterable<AccountPeriod> currencys = periodService.findAll();
			
			return new ResponseEntity<Iterable<AccountPeriod>>(currencys, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/account_period/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountPeriod> getPeriod(@PathVariable("id") Long id) {

		AccountPeriod period = null;

		if(id!=-1)
			period = periodService.findOne(id);
		else {
			period =  new AccountPeriod();

		}

		if(period == null){
			return new ResponseEntity<AccountPeriod>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AccountPeriod>(period, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/account_period", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountPeriod> createPeriod(@RequestBody AccountPeriod period) {

		AccountPeriod savedPeriod =  periodService.create(period);

		return new ResponseEntity<AccountPeriod>(savedPeriod, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/account_period/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<AccountPeriod> updatePeriod(@RequestBody AccountPeriod period) {

		AccountPeriod updatedPeriod = periodService.update(period);

		if(updatedPeriod==null){
			return new ResponseEntity<AccountPeriod>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AccountPeriod>(updatedPeriod, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/account_period/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountPeriod> deletePeriod(@PathVariable("id") Long id, @RequestBody AccountPeriod period){

		periodService.delete(id);

		return new ResponseEntity<AccountPeriod>(HttpStatus.NO_CONTENT);
	}
}
