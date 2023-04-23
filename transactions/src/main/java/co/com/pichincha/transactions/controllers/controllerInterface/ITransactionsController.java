package co.com.pichincha.transactions.controllers.controllerInterface;

import co.com.pichincha.transactions.entity.Transactions;
import co.com.pichincha.transactions.utils.Constant;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ITransactionsController {
    @PostMapping(value = "/insert", produces = {Constant.HEADER_PRODUCES_TRANSACTIONS})
    ResponseEntity<?> insert(@Valid @RequestBody Transactions transactions, BindingResult result);

    @PutMapping(value = "/update/{id}", produces = {Constant.HEADER_PRODUCES_TRANSACTIONS})
    ResponseEntity<?> update(@Valid @RequestBody Transactions transactions,@PathVariable Long id, BindingResult result);

    @DeleteMapping(value = "/delete/{id}", produces = {Constant.HEADER_PRODUCES_TRANSACTIONS})
    ResponseEntity<?> delete(@PathVariable Long id);

    @GetMapping(value = "/read")
    List<Transactions> getAll();

    @GetMapping(value = "/read/{id}")
    ResponseEntity<?> getOne(@PathVariable Long id);

    @GetMapping(value = "/reportes/{id}")
    ResponseEntity<?> getReports(@PathVariable Long id);
}
