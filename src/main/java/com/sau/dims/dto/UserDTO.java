package com.sau.dims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Username can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Username can not contain any non-letter!")
    private String username;
    @NotBlank(message = "Password name can not be null!")
    @Pattern(regexp = "^[a-zA-Z1-9]*$", message = "Password only can be among of a-zA-Z1-9")
    private String password;
    @NotBlank(message = "Adviser's name can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name can not contain any non-letter!")
    private String name;
    @NotBlank(message = "Adviser's surname can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Surname can not contain any non-letter!")
    private String surname;
    private String role;
    private MultipartFile picture;
    @NotBlank(message = "Department can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Department can not contain any non-letter!")
    private String department;
}
