package com.gom.My_Library.models.dto;

import com.gom.My_Library.models.UserRole;

public record RegisterDTO(String name, String login, String password, UserRole role) {
}
