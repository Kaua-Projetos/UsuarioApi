package com.example.dto;

import java.io.Serializable;

public record SenhaDTO(String senha) implements Serializable {

    public SenhaDTO {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres");
        }
    }
}
