package co.com.pichincha.transactions.services.serviceImpl;

import co.com.pichincha.transactions.entity.Transactions;
import co.com.pichincha.transactions.repository.ITransactionsRepository;
import co.com.pichincha.transactions.services.serviceInterface.ITransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements ITransactionsService {
    @Autowired
    private ITransactionsRepository transactionsRepository;

    @Override
    public Transactions findById(Long id) {
        return transactionsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transactions> findAll() {
        return (List<Transactions>) transactionsRepository.findAll();
    }

    @Override
    public Transactions save(Transactions transactions) {
        return transactionsRepository.save(transactions);
    }

    @Override
    public void deleteById(Long id) {
        transactionsRepository.deleteById(id);
    }
}
