package co.com.pichincha.transactions.services.serviceImpl;

import co.com.pichincha.transactions.entity.Accounts;
import co.com.pichincha.transactions.entity.Clients;
import co.com.pichincha.transactions.repository.IAccountsRepository;
import co.com.pichincha.transactions.services.serviceInterface.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements IAccountsService {
    @Autowired
    private IAccountsRepository accountsRepository;

    @Override
    public Accounts findByAccountNumber(String accountNumber) {
        return accountsRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Accounts findById(Long id) {
        return accountsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Accounts> findAll() {
        return (List<Accounts>) accountsRepository.findAll();
    }

    @Override
    public Accounts save(Accounts accounts) {
        return accountsRepository.save(accounts);
    }

    @Override
    public void deleteById(Long id) {
        accountsRepository.deleteById(id);
    }
}
