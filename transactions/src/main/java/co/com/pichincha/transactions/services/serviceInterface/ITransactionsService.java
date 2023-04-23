package co.com.pichincha.transactions.services.serviceInterface;

import co.com.pichincha.transactions.entity.Transactions;

import java.util.List;

public interface ITransactionsService {
    public Transactions findById(Long id);

    public List<Transactions> findAll();

    public Transactions save(Transactions transactions);

    public void deleteById(Long id);
}
