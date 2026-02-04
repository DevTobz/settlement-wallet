package com._jasettlement.demo.util;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public final class WalletUtil {

    private WalletUtil() {
    }

    private static final Map<String, Long> DEFAULT_BALANCES = Map.of(
            "NGN", 0L,
            "USD", 0L
    );

    public static Long defaultWalletBalance(String currencyCode) {
        return DEFAULT_BALANCES.getOrDefault(currencyCode,0L);
    }

    public static Long defaultLedgerBalance(String currencyCode) {
        return DEFAULT_BALANCES.getOrDefault(currencyCode,0L);
    }

    public static String defaultServiceProvider() {
        return "INTERNAL";
    }


    public static String generateWalletName(String currencyCode) {
        return "WALLET-" + currencyCode + "-" + randomSuffix();
    }


    public static String generateWalletPublicId() {
        return UUID.randomUUID().toString();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    private static String randomSuffix() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
    }
}
