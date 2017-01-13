package inforbis.erp.controller.base;

import inforbis.erp.model.base.Department;
import inforbis.erp.service.base.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value= "/erp/department", method=RequestMethod.GET,
			                                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Department>> getDepartments() {
	
			Iterable<Department> departments = departmentService.findAll();
			
			return new ResponseEntity<Iterable<Department>>(departments, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/department/{id}", method = RequestMethod.GET,
			                                     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id) {

		Department department = departmentService.findOne(id);

		if(department == null){
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Department>(department, HttpStatus.OK);

	}

	@RequestMapping(value= "/erp/department", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {

		Department savedDepartment =  departmentService.create(department);

		return new ResponseEntity<Department>(savedDepartment, HttpStatus.CREATED);

	}

	@RequestMapping(value= "/erp/department/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {

		Department updatedDepartment = departmentService.update(department);

		if(updatedDepartment==null){
			return new ResponseEntity<Department>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Department>(updatedDepartment, HttpStatus.OK);
	}

	@RequestMapping(value= "/erp/department/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long id, @RequestBody Department department){

		departmentService.delete(id);

		return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);

	}
}
