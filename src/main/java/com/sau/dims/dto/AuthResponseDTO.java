package com.sau.dims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
