package com.bandeira.sistema_venda_de_ingressos.models.enums;

public enum UserRole {

    ADMIN("user"),

    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
