package com.bka.ssi.controller.verification.company.services.utilities;

import java.security.SecureRandom;

public class NonceGenerator {

    private final static SecureRandom secureRandom = new SecureRandom();

    public static int nextPositiveInt() {
        return secureRandom.nextInt() & Integer.MAX_VALUE;
    }

    public static int nextPositiveIntExcludingIntMaxValue() {
        return secureRandom.nextInt(Integer.MAX_VALUE);
    }

    public static int notSecureNextPositiveIntFavoringAllBesidesZero() {
        return Math.abs(secureRandom.nextInt());
    }
}
