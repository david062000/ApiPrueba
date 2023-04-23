package co.com.pichincha.transactions.services.serviceImpl;

import co.com.pichincha.transactions.entity.Clients;
import co.com.pichincha.transactions.entity.Transactions;
import co.com.pichincha.transactions.repository.IClientsRepository;
import co.com.pichincha.transactions.services.serviceInterface.IClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsServiceImpl implements IClientsService {
    @Autowired
    private IClientsRepository clientsRepository;

    @Override
    public Clients findByClientId(String clientId) {
        return clientsRepository.findByClientId(clientId);
    }

    @Override
    public Clients findById(Long id) {
        return clientsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Clients> findAll() {
        return (List<Clients>) clientsRepository.findAll();
    }

    @Override
    public Clients save(Clients clients) {
        return clientsRepository.save(clients);
    }

    @Override
    public void deleteById(Long id) {
        clientsRepository.deleteById(id);
    }
}
