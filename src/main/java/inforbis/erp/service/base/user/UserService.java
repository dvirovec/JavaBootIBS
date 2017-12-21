package inforbis.erp.service.base.user;

import inforbis.erp.model.base.User;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface UserService {

    Iterable<User> findAll();

    User findByName(String name);
    User findOne(Long id);
    User create(User user);
    User update(User User);
    void delete(Long id);

}
