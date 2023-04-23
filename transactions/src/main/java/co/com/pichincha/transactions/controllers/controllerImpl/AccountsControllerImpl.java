package co.com.pichincha.transactions.controllers.controllerImpl;

import co.com.pichincha.transactions.controllers.controllerInterface.IAccountsController;
import co.com.pichincha.transactions.entity.Accounts;
import co.com.pichincha.transactions.entity.Clients;
import co.com.pichincha.transactions.entity.Persons;
import co.com.pichincha.transactions.services.serviceInterface.IAccountsService;
import co.com.pichincha.transactions.services.serviceInterface.IClientsService;
import co.com.pichincha.transactions.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constant.INITIAL_VALUE_ACCOUNTS_END_POINTS)
@CrossOrigin(origins = Constant.USE_PAGE)
public class AccountsControllerImpl implements IAccountsController {

    @Autowired
    private HttpServletRequest headers;

    @Autowired
    private IAccountsService accountsService;

    @Override
    public ResponseEntity<?> insert(Accounts accounts, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put(Constant.ERROR, errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            response.put(Constant.ACCOUNT, accountsService.save(accounts));
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> update(Accounts accounts,String id, BindingResult result) {
        Accounts accountCurrent = accountsService.findByAccountNumber(id);
        Accounts accountUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put(Constant.ERROR, errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (accountCurrent == null) {
            response.put(Constant.MESSAGE, Constant.EDIT_MESSAGE_ERROR
                    .concat(accounts.getId().toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            accountCurrent.setAccountType(accounts.getAccountType());
            accountCurrent.setAccountNumber(accounts.getAccountNumber());
            accountCurrent.setInitialBalance(accounts.getInitialBalance());
            accountCurrent.setStatus(accounts.getStatus());
            accountUpdate = accountsService.save(accountCurrent);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(Constant.MESSAGE, Constant.UPDATE_MESSAGE);
        response.put(Constant.ACCOUNT, accountUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            accountsService.deleteById(id);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.DELETE_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constant.MESSAGE, Constant.DELETE_MESSAGE);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    public List<Accounts> getAll() {
        return accountsService.findAll();
    }

    @Override
    public ResponseEntity<?> getOne(String id) {
        Accounts account = null;
        Map<String, Object> response = new HashMap<>();

        try {
            account = accountsService.findByAccountNumber(id);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.GET_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (account == null) {
            response.put(Constant.MESSAGE, "El ID: ".concat(id.toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Accounts>(account, HttpStatus.OK);
    }
}
