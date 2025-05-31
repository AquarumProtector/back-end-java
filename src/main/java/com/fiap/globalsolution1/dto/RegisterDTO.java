package com.fiap.globalsolution1.dto;

import com.fiap.globalsolution1.model.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
