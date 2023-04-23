package co.com.pichincha.transactions.repository;

import co.com.pichincha.transactions.entity.Clients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IClientsRepository extends CrudRepository<Clients, Long> {
    @Query("select e from Clients e where e.clientId=?1")
    public Clients findByClientId(String clientId);
}
