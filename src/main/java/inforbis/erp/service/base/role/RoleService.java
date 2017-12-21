package inforbis.erp.service.base.role;

import inforbis.erp.model.base.Role;
import inforbis.erp.model.financial.account.Account;

/**
 * Created by dvirovec on 31.5.2017..
 */
public interface RoleService {

    Iterable<Role> findAll();
    Iterable<Role> findRoleByName(String name);
    Role findOne(Long id);
    Role create(Role role);
    Role update(Role role);
    void delete(Long id);

}
