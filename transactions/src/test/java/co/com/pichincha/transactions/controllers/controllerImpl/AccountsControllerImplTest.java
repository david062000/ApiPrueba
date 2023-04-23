package co.com.pichincha.transactions.controllers.controllerImpl;

import static co.com.pichincha.transactions.util.RequestResponseMock.mockAccountsResponse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import co.com.pichincha.transactions.services.serviceInterface.IAccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.io.IOException;

public class AccountsControllerImplTest {
    @Mock
    private IAccountsService accountsService;

    @InjectMocks
    private AccountsControllerImpl accountsController;

    @BeforeEach
    void init() throws IOException {
    }

    /*@Test
    @DisplayName("return accounts ok get")
    void returnAccountsOkGet() throws IOException {
        when(accountsService.findByAccountNumber("585545"))
                .thenReturn(mockAccountsResponse());
        assertNotNull(accountsController.getOne("585545"));
    }*/
}
