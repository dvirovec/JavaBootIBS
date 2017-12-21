package inforbis.erp.service.base.role;

import inforbis.erp.model.base.Role;
import inforbis.erp.repository.base.RoleRepository;
import inforbis.erp.service.ErpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 31.5.2017..
 */
@Service
public class RoleServiceBean extends ErpService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        Iterable<Role> accounts = roleRepository.findAll(getClientId());
        return accounts;
    }


    @Override
    public Iterable<Role> findRoleByName(String name) {
        Iterable<Role> roles = roleRepository.findRoleByName(name, getClientId());
        return roles;
    }

    @Override
    public Role findOne(Long id) {
        Role account = roleRepository.findOne(id);
        return account;
    }

    @Override
    public Role create(Role account) {
        if(account.getId()!=null) {
            return null;
        }
        account.setClient_id(getClientId());
        Role savedAccount = roleRepository.save(account);
        return savedAccount;
    }

    @Override
    public Role update(Role role) {

        Role rolePersisted = findOne(role.getId());

        if(rolePersisted==null) {
            return null;
        }

        Role updatedAccount = roleRepository.save(role);
        return updatedAccount;
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    
}
