package co.com.pichincha.transactions.controllers.controllerImpl;

import co.com.pichincha.transactions.controllers.controllerInterface.ITransactionsController;
import co.com.pichincha.transactions.dto.Reports;
import co.com.pichincha.transactions.entity.Accounts;
import co.com.pichincha.transactions.entity.Transactions;
import co.com.pichincha.transactions.services.serviceInterface.IAccountsService;
import co.com.pichincha.transactions.services.serviceInterface.ITransactionsService;
import co.com.pichincha.transactions.utils.CalculateBalance;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constant.INITIAL_VALUE_TRANSACTIONS_END_POINTS)
@CrossOrigin(origins = Constant.USE_PAGE)
public class TransactionsControllerImpl implements ITransactionsController {
    @Autowired
    private HttpServletRequest headers;

    @Autowired
    private ITransactionsService transactionsService;

    @Autowired
    private IAccountsService accountsService;

    @Autowired
    private CalculateBalance calculateBalance;

    @Autowired
    private Reports reports;

    @Override
    public ResponseEntity<?> insert(Transactions transactions, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put(Constant.ERROR, errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Accounts accountCurrent = accountsService.findById(transactions.getAccounts().getId());
            if (calculateBalance.allowOperation(accountCurrent.getCurrentBalance(),
                    transactions.getValue(), transactions.getMotionType())) {
                Double resultOperation = calculateBalance.calculate(accountCurrent.getCurrentBalance(),
                        transactions.getValue(), transactions.getMotionType());
                accountCurrent.setCurrentBalance(resultOperation);
                transactions.setBalance(resultOperation);

                accountsService.save(accountCurrent);
                response.put(Constant.TRANSACTION, transactionsService.save(transactions));
                response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
            }
            response.put(Constant.MESSAGE, Constant.NO_MONEY);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> update(Transactions transactions, Long id,BindingResult result) {
        Transactions transactionCurrent = transactionsService.findById(id);
        Transactions transactionUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put(Constant.ERROR, errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (transactionCurrent == null) {
            response.put(Constant.MESSAGE, Constant.EDIT_MESSAGE_ERROR
                    .concat(transactions.getId().toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            transactionCurrent.setBalance(transactions.getBalance());
            transactionCurrent.setDate(transactions.getDate());
            transactionCurrent.setValue(transactions.getValue());
            transactionCurrent.setMotionType(transactions.getMotionType());
            transactionUpdate = transactionsService.save(transactionCurrent);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(Constant.MESSAGE, Constant.UPDATE_MESSAGE);
        response.put(Constant.TRANSACTION, transactionUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            transactionsService.deleteById(id);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.DELETE_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constant.MESSAGE, Constant.DELETE_MESSAGE);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    public List<Transactions> getAll() {
        return transactionsService.findAll();
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Transactions transactions = null;
        Map<String, Object> response = new HashMap<>();

        try {
            transactions = transactionsService.findById(id);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.GET_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (transactions == null) {
            response.put(Constant.MESSAGE, "El ID: ".concat(id.toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getReports(Long id) {
        List<Transactions> transactions = null;
        Map<String, Object> response = new HashMap<>();

        try {
            transactions = transactionsService.findAll();
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.GET_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (transactions == null) {
            response.put(Constant.MESSAGE, "El ID: ".concat(id.toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        List<Transactions> filterReports = transactions.stream().filter(result ->
                result.getAccounts().getClient().getId()
                .equals(id))
                .collect(Collectors.toList());
        List<Reports> reportResponse = new ArrayList<>();

        for (Transactions rep: filterReports) {
            reports.setDate(rep.getDate());
            reports.setClient(rep.getAccounts().getClient().getName());
            reports.setAccountNumber(rep.getAccounts().getAccountNumber());
            reports.setType(rep.getAccounts().getAccountType());
            reports.setInitialBalance(rep.getAccounts().getInitialBalance());
            reports.setStatus(rep.getAccounts().getStatus());
            reports.setMotion(rep.getValue());
            reports.setCurrentBalance(rep.getAccounts().getCurrentBalance());
            reportResponse.add(reports);
        }
        return new ResponseEntity<List<Reports>>(reportResponse, HttpStatus.OK);
    }
}
