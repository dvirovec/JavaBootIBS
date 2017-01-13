package inforbis.erp.service.base.department;

import inforbis.erp.model.base.Department;
import inforbis.erp.repository.base.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class DepartmentServiceBean implements DepartmentService {

    @Autowired
    private DepartmentRepository deparmentRepository;

    @Override
    public Iterable<Department> findAll() {

        Iterable<Department> deparments = deparmentRepository.findAll();

        return deparments;
    }

    @Override
    public Department findOne(Long id) {

        Department deparment = deparmentRepository.findOne(id);

        return deparment;
    }

    @Override
    public Department create(Department deparment) {

        if(deparment.getId()!=null) {
            return null;
        }

        Department savedDepartment = deparmentRepository.save(deparment);

        return savedDepartment;

    }

    @Override
    public Department update(Department deparment) {

        Department deparmentPersisted = findOne(deparment.getId());
                if(deparmentPersisted== null){
                    return null;
                }

        Department updatedCompany = deparmentRepository.save(deparment);

        return updatedCompany;
    }

    @Override
    public void delete(Long id) {

        deparmentRepository.delete(id);

    }
}
