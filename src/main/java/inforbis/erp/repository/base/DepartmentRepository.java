package inforbis.erp.repository.base;

import inforbis.erp.model.base.Department;
import inforbis.erp.model.base.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
