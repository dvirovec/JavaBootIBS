package inforbis.erp.service.hr.employee;

import inforbis.erp.model.base.Employee;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface EmployeeService {

    Iterable<Employee> findAll();
    Employee findOne(Long id);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(Long id);

}
