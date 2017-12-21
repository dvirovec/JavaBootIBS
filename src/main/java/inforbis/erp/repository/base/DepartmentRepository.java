package inforbis.erp.repository.base;

import inforbis.erp.model.base.Department;
import inforbis.erp.model.base.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query("select a from Department a where a.client_id = :client_id and a.name like :name"+"%" )
    Iterable<Department> findDepartmentByName(@Param("client_id") Long client_id, @Param("name") String name);

    @Query("select a from Department a where a.client_id = :client_id")
    Iterable<Department> findAll(@Param("client_id") Long client_id);

}
