package inforbis.erp.service.base.department;

import inforbis.erp.model.base.Department;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface DepartmentService {

    Iterable<Department> findAll();
    Department findOne(Long id);
    Department create(Department user);
    Department update(Department Department);
    void delete(Long id);

}
