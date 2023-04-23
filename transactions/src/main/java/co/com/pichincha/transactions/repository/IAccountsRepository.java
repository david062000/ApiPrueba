package co.com.pichincha.transactions.repository;

import co.com.pichincha.transactions.entity.Accounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IAccountsRepository extends CrudRepository<Accounts, Long> {
    @Query("select e from Accounts e where e.accountNumber=?1")
    public Accounts findByAccountNumber(String accountNumber);
}
