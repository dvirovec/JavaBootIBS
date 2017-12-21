package inforbis.erp.service.base.town;

import inforbis.erp.model.base.Town;
import inforbis.erp.model.base.Town;
import inforbis.erp.repository.base.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class TownServiceBean implements TownService {

    @Autowired
    private TownRepository townRepository;

    @Override
    public Iterable<Town> findAll() {

        Iterable<Town> towns = townRepository.findAll();

        return towns;
    }

    @Override
    public Town findOne(Long id) {

        Town town = townRepository.findOne(id);

        return town;
    }

    @Override
    public Iterable<Town> findByCountryCode(String countryCode) {

        Iterable<Town> towns = townRepository.findByCountryCode(countryCode);

        return towns;
    }

    @Override
    public Iterable<Town> findByName(String name) {

        Iterable<Town> towns = townRepository.findByNameStartingWithIgnoreCase(name);

        return towns;
    }

    @Override
    public Town create(Town town) {

        if(town.getId()!=null) {
            return null;
        }

        Town savedTown = townRepository.save(town);

        return savedTown;
    }

    @Override
    public Town update(Town town) {

        Town townPersisted = findOne(town.getId());
                if(townPersisted== null){
                    return null;
                }

        Town updatedTown = townRepository.save(town);

        return updatedTown;
    }

    @Override
    public void delete(Long id) {

        townRepository.delete(id);

    }
}
