package inforbis.erp.service.base.client;

import inforbis.erp.model.base.Client;
import inforbis.erp.repository.base.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 25.5.2017..
 */

@Service
public class ClientServiceBean implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Iterable<Client> findClientByName(String clientName) {
        return clientRepository.findClientByName(clientName);
    }

    @Override
    public Client findOne(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public Client create(Client client) {

        if(client.getId()!=null) {
            return null;
        }

        Client savedClient = clientRepository.save(client);

        return savedClient;
    }

    @Override
    public Client update(Client client) {

        Client clientPersisted = findOne(client.getId());
        if(clientPersisted== null){
            return null;
        }

        Client updatedCompany = clientRepository.save(client);

        return updatedCompany;

    }

    @Override
    public void delete(Long id) {

        clientRepository.delete(id);

    }
}
