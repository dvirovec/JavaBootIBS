package inforbis.erp.service.other;

import inforbis.erp.model.other.Greeting;

import java.util.Collection;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface GreetingService {

    Iterable<Greeting> findAll();
    Greeting findOne(Long id);
    Greeting create(Greeting gretting);
    Greeting update(Greeting greeting);
    void delete(Long id);

}
