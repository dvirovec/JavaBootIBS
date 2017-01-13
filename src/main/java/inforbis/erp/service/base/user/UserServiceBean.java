package inforbis.erp.service.base.user;

import inforbis.erp.model.base.Company;
import inforbis.erp.model.base.User;
import inforbis.erp.repository.base.CompanyRepository;
import inforbis.erp.repository.base.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {

        Iterable<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User findOne(Long id) {

        User user = userRepository.findOne(id);

        return user;
    }

    @Override
    public User findByName(String name) {

        User user = userRepository.findByName(name);

        return user;
    }


    @Override
    public User create(User user) {

        if(user.getId()!=null) {
            return null;
        }

        User savedUser = userRepository.save(user);

        return savedUser;

    }

    @Override
    public User update(User user) {

        User userPersisted = findOne(user.getId());
                if(userPersisted== null){
                    return null;
                }

        User updatedCompany = userRepository.save(user);

        return updatedCompany;
    }

    @Override
    public void delete(Long id) {

        userRepository.delete(id);

    }
}
