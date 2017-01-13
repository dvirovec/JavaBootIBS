package inforbis.erp.service.base.user_detail;

import inforbis.erp.model.base.User;
import inforbis.erp.model.base.UserDetail;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface UserDetailService {

    Iterable<UserDetail> findAll();

    UserDetail findByName(String name);
    UserDetail findOne(Long id);
    UserDetail create(UserDetail user_detail);
    UserDetail update(UserDetail User_detail);
    void delete(Long id);

}
