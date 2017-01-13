package inforbis.erp.service.other;

import inforbis.erp.repository.other.GreetingRepository;
import inforbis.erp.model.other.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class GreetingServiceBean implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Iterable<Greeting> findAll() {

        Iterable<Greeting> greetings = greetingRepository.findAll();

        return greetings;
    }

    @Override
    public Greeting findOne(Long id) {

        Greeting greeting = greetingRepository.findOne(id);

        return greeting;
    }

    @Override
    public Greeting create(Greeting greeting) {

        if(greeting.getId()!=null) {
            return null;
        }

        Greeting savedGreeting = greetingRepository.save(greeting);

        return savedGreeting;

    }

    @Override
    public Greeting update(Greeting greeting) {

        Greeting greetingPersisted = findOne(greeting.getId());
                if(greetingPersisted== null){
                    return null;
                }

        Greeting updatedGreeting = greetingRepository.save(greeting);

        return updatedGreeting;
    }

    @Override
    public void delete(Long id) {

        greetingRepository.delete(id);

    }
}
