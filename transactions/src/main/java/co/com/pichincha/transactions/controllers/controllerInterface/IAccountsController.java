package co.com.pichincha.transactions.controllers.controllerInterface;

import co.com.pichincha.transactions.entity.Accounts;
import co.com.pichincha.transactions.utils.Constant;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IAccountsController {
    @PostMapping(value = "/insert", produces = {Constant.HEADER_PRODUCES_TRANSACTIONS})
    ResponseEntity<?> insert(@Valid @RequestBody Accounts accounts, BindingResult result);

    @PutMapping(value = "/update/{id}", produces = {Constant.HEADER_PRODUCES_TRANSACTIONS})
    ResponseEntity<?> update(@Valid @RequestBody Accounts accounts,@PathVariable String id, BindingResult result);

    @DeleteMapping(value = "/delete/{id}", produces = {Constant.HEADER_PRODUCES_TRANSACTIONS})
    ResponseEntity<?> delete(@PathVariable Long id);

    @GetMapping(value = "/read")
    List<Accounts> getAll();

    @GetMapping(value = "/read/{id}")
    ResponseEntity<?> getOne(@PathVariable String id);
}
