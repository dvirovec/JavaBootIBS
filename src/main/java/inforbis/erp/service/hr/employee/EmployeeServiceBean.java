package inforbis.erp.service.hr.employee;

import inforbis.erp.model.base.Employee;
import inforbis.erp.repository.hr.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class EmployeeServiceBean implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> findAll() {

        Iterable<Employee> employees = employeeRepository.findAll();

        return employees;
    }

    @Override
    public Employee findOne(Long id) {

        Employee employee = employeeRepository.findOne(id);

        return employee;
    }

    @Override
    public Employee create(Employee employee) {

        if(employee.getId()!=null) {
            return null;
        }

        Employee savedEmployee = employeeRepository.save(employee);

        return savedEmployee;
    }

    @Override
    public Employee update(Employee employee) {

        Employee employeePersisted = findOne(employee.getId());
                if(employeePersisted== null){
                    return null;
                }

        Employee updatedEmployee = employeeRepository.save(employee);

        return updatedEmployee;
    }

    @Override
    public void delete(Long id) {

        employeeRepository.delete(id);

    }
}
