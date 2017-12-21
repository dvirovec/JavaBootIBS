package inforbis.erp.service.base.company;

import inforbis.erp.model.base.Client;
import inforbis.erp.repository.base.CompanyRepository;
import inforbis.erp.model.base.Company;
import inforbis.erp.service.ErpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class CompanyServiceBean extends ErpService implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Iterable<Company> findAll() {


        Iterable<Company> companys = companyRepository.findAll(getClientId());

        return companys;
    }

    @Override
    public Iterable<Company> findCompanyByName( String companyName) {

        return companyRepository.findCompanyByName(getClientId(), companyName);
    }

    @Override
    public Company findOne(Long id) {

        Company company = companyRepository.findOne(id);

        return company;
    }

    @Override
    public Company create(Company company) {

        if(company.getId()!=null) {
            return null;
        }

        company.setClient_id(getClientId());

        Company savedCompany = companyRepository.save(company);

        return savedCompany;

    }

    @Override
    public Company update(Company company) {

        Company companyPersisted = findOne(company.getId());
                if(companyPersisted== null){
                    return null;
                }

        Company updatedCompany = companyRepository.save(company);

        return updatedCompany;
    }

    @Override
    public void delete(Long id) {

        companyRepository.delete(id);

    }
}
