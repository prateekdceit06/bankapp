package org.backend.controllers.account;

import java.util.Random;

public class GenerateAccountNumber {
    public String generateAccountNumber() {
        int leftLimit = 49;
        int rightLimit = 57;
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
