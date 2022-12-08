package org.backend.models;

import org.backend.staticdata.Data;

import java.time.LocalDateTime;

public class AccountFactory {
    public Account createAccount(int customerId, String accountNumber, String accountType,
                                 double accountBalance, int accountStatus,
                                 LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        if (accountType.equals(Data.AccountTypes.CHECKING.toString())) {
            return new AccountChecking(customerId, accountNumber, accountType, accountBalance, accountStatus,
                    accountCreationDate, accountLastUpdatedDate);
        } else if (accountType.equals(Data.AccountTypes.SAVINGS.toString())) {
            return new AccountSavings(customerId, accountNumber, accountType, accountBalance, accountStatus,
                    accountCreationDate, accountLastUpdatedDate);
        } else if (accountType.equals(Data.AccountTypes.LOAN.toString())) {
            return new AccountLoan(customerId, accountNumber, accountType, accountBalance, accountStatus,
                    accountCreationDate, accountLastUpdatedDate);
        }
        return null;
    }
}
