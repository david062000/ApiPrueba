package co.com.pichincha.transactions.services.serviceInterface;

import co.com.pichincha.transactions.entity.Clients;

import java.util.List;

public interface IClientsService {
    public Clients findByClientId(String clientId);

    public Clients findById(Long id);

    public List<Clients> findAll();

    public Clients save(Clients clients);

    public void deleteById(Long id);
}
