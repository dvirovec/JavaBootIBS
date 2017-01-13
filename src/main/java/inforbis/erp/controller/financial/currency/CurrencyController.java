package inforbis.erp.controller.financial.currency;


import inforbis.erp.model.financial.currency.Currency;
import inforbis.erp.service.financial.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping(value= "/erp/currency", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Currency>> getCurrencys() {
	
			Iterable<Currency> currencys = currencyService.findAll();
			
			return new ResponseEntity<Iterable<Currency>>(currencys, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/currency/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Currency> getCurrency(@PathVariable("id") Long id) {

		Currency currency = currencyService.findOne(id);

		if(currency == null){
			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Currency>(currency, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/currency", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {

		Currency savedCurrency =  currencyService.create(currency);

		return new ResponseEntity<Currency>(savedCurrency, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/currency/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Currency> updateCurrency(@RequestBody Currency currency) {

		Currency updatedCurrency = currencyService.update(currency);

		if(updatedCurrency==null){
			return new ResponseEntity<Currency>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Currency>(updatedCurrency, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/currency/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Currency> deleteCurrency(@PathVariable("id") Long id, @RequestBody Currency currency){

		currencyService.delete(id);

		return new ResponseEntity<Currency>(HttpStatus.NO_CONTENT);

	}
}
