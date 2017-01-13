package inforbis.erp.repository.other;

import inforbis.erp.model.other.Greeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long> {


}
