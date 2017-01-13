package inforbis.erp.service.base.user_detail;

import inforbis.erp.model.base.User;
import inforbis.erp.model.base.UserDetail;
import inforbis.erp.repository.base.UserDetailRepository;
import inforbis.erp.repository.base.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class UserDetailServiceBean implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public Iterable<UserDetail> findAll() {

        Iterable<UserDetail> user_details = userDetailRepository.findAll();

        return user_details;
    }

    @Override
    public UserDetail findOne(Long id) {

        UserDetail user = userDetailRepository.findOne(id);

        return user;
    }

    @Override
    public UserDetail findByName(String name) {

        UserDetail user_detail = userDetailRepository.findByLastName(name);

        return user_detail;
    }

    @Override
    public UserDetail create(UserDetail user_detail) {

        if(user_detail.getId()!=null) {
            return null;
        }

        UserDetail savedUserDetail = userDetailRepository.save(user_detail);

        return savedUserDetail;

    }

    @Override
    public UserDetail update(UserDetail user_detail) {

        UserDetail userDetailPersisted = findOne(user_detail.getId());
                if(userDetailPersisted== null){
                    return null;
                }

        UserDetail updatedUserDetail = userDetailRepository.save(user_detail);

        return updatedUserDetail;
    }

    @Override
    public void delete(Long id) {

        userDetailRepository.delete(id);

    }
}
