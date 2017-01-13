package inforbis.erp.repository.hr;

import inforbis.erp.model.base.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {




}
