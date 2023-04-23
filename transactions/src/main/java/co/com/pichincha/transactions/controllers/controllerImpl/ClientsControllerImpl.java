package co.com.pichincha.transactions.controllers.controllerImpl;

import co.com.pichincha.transactions.controllers.controllerInterface.IClientsController;
import co.com.pichincha.transactions.entity.Clients;
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
@RequestMapping(Constant.INITIAL_VALUE_CLIENTS_END_POINTS)
@CrossOrigin(origins = Constant.USE_PAGE)
public class ClientsControllerImpl implements IClientsController {

    @Autowired
    private HttpServletRequest headers;

    @Autowired
    private IClientsService clientsService;

    @Override
    public ResponseEntity<?> insert(Clients clients, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put(Constant.ERROR, errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            response.put(Constant.CLIENT, clientsService.save(clients));
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> update(Clients clients,String id, BindingResult result) {
        Clients clientCurrent = clientsService.findByClientId(id);
        Clients clientUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put(Constant.ERROR, errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (clientCurrent == null) {
            response.put(Constant.MESSAGE, Constant.EDIT_MESSAGE_ERROR
                    .concat(clients.getId().toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            clientCurrent.setName(clients.getName());
            clientCurrent.setClientId(clients.getClientId());
            clientCurrent.setPassword(clients.getPassword());
            clientCurrent.setStatus(clients.getStatus());
            clientCurrent.setAge(clients.getAge());
            clientCurrent.setAddress(clients.getAddress());
            clientCurrent.setGender(clients.getGender());
            clientCurrent.setIdentification(clients.getIdentification());
            clientCurrent.setTelephone(clients.getTelephone());
            clientUpdate = clientsService.save(clientCurrent);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.CREATED_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(Constant.MESSAGE, Constant.UPDATE_MESSAGE);
        response.put(Constant.CLIENT, clientUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            clientsService.deleteById(id);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.DELETE_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constant.MESSAGE, Constant.DELETE_MESSAGE);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    public List<Clients> getAll() {
        return clientsService.findAll();
    }

    @Override
    public ResponseEntity<?> getOne(String id) {
        Clients client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            client = clientsService.findByClientId(id);
        } catch (DataAccessException e) {
            response.put(Constant.MESSAGE, Constant.GET_MESSAGE_ERROR);
            response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (client == null) {
            response.put(Constant.MESSAGE, "El ID: ".concat(id.toString().concat(Constant.NO_CONTENT_MESSAGE_ERROR)));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Clients>(client, HttpStatus.OK);
    }
}
