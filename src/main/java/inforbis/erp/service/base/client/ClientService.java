package inforbis.erp.service.base.client;


import inforbis.erp.model.base.Client;

/**
 * Created by dvirovec on 25.5.2017..
 */
public interface ClientService {

    Iterable<Client> findAll();
    Iterable<Client> findClientByName(String clientName);

    Client findOne(Long id);
    Client create(Client client);
    Client update(Client client);
    void delete(Long id);

}
