package inforbis.erp.repository.base;

import inforbis.erp.model.base.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 25.5.2017..
 */
public interface ClientRepository extends CrudRepository<Client,Long> {

    @Query("select a from Client a where a.clientName like :clientName"+"%" )
    Iterable<Client> findClientByName(@Param("clientName") String clientName);

    @Query("select a from Client a where id = :client_id")
    Iterable<Client> findAll(@Param("client_id") Long client_id);

}
