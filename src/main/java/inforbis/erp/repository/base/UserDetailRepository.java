package inforbis.erp.repository.base;

import inforbis.erp.model.base.UserDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dvirovec on 25.12.2016..
 */
public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {

    UserDetail findByLastName(String name);


}
