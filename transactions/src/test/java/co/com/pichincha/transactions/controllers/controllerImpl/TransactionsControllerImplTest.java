package co.com.pichincha.transactions.controllers.controllerImpl;

import co.com.pichincha.transactions.services.serviceInterface.ITransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static co.com.pichincha.transactions.util.RequestResponseMock.mockTransactionResponse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class TransactionsControllerImplTest {
    @Mock
    private ITransactionsService transactionsService;

    @InjectMocks
    private TransactionsControllerImpl transactionsController;

    @BeforeEach
    void init() throws IOException {
    }

    /*@Test
    @DisplayName("return transaction ok get")
    void returnTransactionOkGet() throws IOException {
        when(transactionsService.findById(Long.valueOf(1)))
                .thenReturn(mockTransactionResponse());
        assertNotNull(transactionsController.getOne(Long.valueOf(1)));
    }*/
}
