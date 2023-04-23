package co.com.pichincha.transactions.services.serviceInterface;

import co.com.pichincha.transactions.entity.Accounts;
import co.com.pichincha.transactions.entity.Clients;

import java.util.List;

public interface IAccountsService {
    public Accounts findByAccountNumber(String accountNumber);

    public Accounts findById(Long id);

    public List<Accounts> findAll();

    public Accounts save(Accounts accounts);

    public void deleteById(Long id);
}
