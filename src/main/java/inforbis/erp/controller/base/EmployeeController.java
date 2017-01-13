package inforbis.erp.controller.base;

import inforbis.erp.model.base.Employee;
import inforbis.erp.service.hr.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value= "/erp/employee", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Employee>> getEmployees() {
	
			Iterable<Employee> employees = employeeService.findAll();
			
			return new ResponseEntity<Iterable<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/employee/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {

		Employee employee = employeeService.findOne(id);

		if(employee == null){
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/employee", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

		Employee savedEmployee =  employeeService.create(employee);

		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/employee/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {

		Employee updatedEmployee = employeeService.update(employee);

		if(updatedEmployee==null){
			return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/employee/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){

		employeeService.delete(id);

		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);

	}
}
