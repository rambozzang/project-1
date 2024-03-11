package com.tigerbk.project1.enums;

import java.util.Arrays;

public enum AuthProvider {
    GOOGLE("GOOGLE"),
    FACEBOOK("FACEBOOK"),
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    EMPTY("");

    private String authProvider;

    public String getAuthProvider() {
        return authProvider;
    }

    AuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public static AuthProvider findByCode(String code) {
        return Arrays.stream(AuthProvider.values())
                .filter(provider -> provider.getAuthProvider().equals(code.toUpperCase()))
                .findFirst()
                .orElse(EMPTY);
    }
}