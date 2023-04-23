package co.com.pichincha.transactions.repository;

import co.com.pichincha.transactions.entity.Transactions;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionsRepository extends CrudRepository<Transactions, Long> {
}
