package inforbis.erp.repository.hr;

import inforbis.erp.model.base.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select a from Employee a where a.client_id = :client_id and a.surname like :surname"+"%" )
    Iterable<Employee> findEmployeeBySurName(@Param("client_id") Long client_id, @Param("surname") String surname);

    @Query("select a from Employee a where a.client_id = :client_id")
    Iterable<Employee> findAll(@Param("client_id") Long client_id);


}
