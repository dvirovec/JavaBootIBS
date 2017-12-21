package inforbis.erp.repository.base;

import inforbis.erp.model.base.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 31.5.2017..
 */
public interface RoleRepository extends CrudRepository<Role,Long> {

    @Query("select a from Role a where a.name = :role_name and a.client_id = :client_id")
    Iterable<Role> findRoleByName(@Param("role_name") String role_name, @Param("client_id")Long client_id);

    @Query("select a from Role a where a.client_id = :client_id")
    Iterable<Role> findAll(@Param("client_id") Long client_id);


}
