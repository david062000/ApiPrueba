package co.com.pichincha.transactions.util;

import co.com.pichincha.transactions.entity.Accounts;
import co.com.pichincha.transactions.entity.Transactions;

import java.io.IOException;

public class RequestResponseMock {
    public static Accounts mockAccountsResponse() throws IOException {
        return MockFilesReader
                .readObject("response/AccountsResponse.json",
                        Accounts.class, ClassLoader.getSystemClassLoader());
    }

    public static Transactions mockTransactionResponse() throws IOException {
        return MockFilesReader
                .readObject("response/TransactionResponse.json",
                        Transactions.class, ClassLoader.getSystemClassLoader());
    }
}
