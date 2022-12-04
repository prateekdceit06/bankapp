package org.backend.models;

import java.time.LocalDateTime;

public class AccountChecking extends Account{
    public AccountChecking(int customerId, String accountType, double accountBalance, int accountStatus) {
        super(customerId, accountType, accountBalance, accountStatus);
    }

    public AccountChecking(int customerId, String accountNumber, String accountType, double accountBalance,
                           int accountStatus, LocalDateTime accountCreationDate, LocalDateTime accountLastUpdatedDate) {
        super(customerId, accountNumber, accountType, accountBalance, accountStatus, accountCreationDate, accountLastUpdatedDate);
    }
}
