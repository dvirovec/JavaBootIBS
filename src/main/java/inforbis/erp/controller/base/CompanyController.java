package inforbis.erp.controller.base;

import inforbis.erp.model.base.Company;
import inforbis.erp.service.base.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value= "/erp/company", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Company>> getCompanys() {


			Iterable<Company> companys = companyService.findAll();
			
			return new ResponseEntity<Iterable<Company>>(companys, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/company_name/{company_name}", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Company>> getCompanysByName(@PathVariable("company_name") String company_name) {

		Iterable<Company> companys = companyService.findCompanyByName(company_name);

		return new ResponseEntity<Iterable<Company>>(companys, HttpStatus.OK);
	}


	@RequestMapping(value= "/erp/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> getCompany(@PathVariable("id") Long id) {

		Company company = companyService.findOne(id);

		if(company == null){
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Company>(company, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/company", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {

		Company savedCompany =  companyService.create(company);

		return new ResponseEntity<Company>(savedCompany, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/company/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Company> updateCompany(@RequestBody Company company) {

		Company updatedCompany = companyService.update(company);

		if(updatedCompany==null){
			return new ResponseEntity<Company>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Company>(updatedCompany, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/company/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id, @RequestBody Company company){

		companyService.delete(id);

		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);

	}
}
