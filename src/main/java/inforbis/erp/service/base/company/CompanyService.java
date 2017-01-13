package inforbis.erp.service.base.company;

import inforbis.erp.model.base.Company;

import java.util.Collection;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface CompanyService {

    Iterable<Company> findAll();

    Iterable<Company> findCompanyByName(String companyName);
    Company findOne(Long id);
    Company create(Company gretting);
    Company update(Company Company);
    void delete(Long id);

}
