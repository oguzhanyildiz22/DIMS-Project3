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
public class AdviserUpdateDTO {

    private int id;
    @NotBlank(message = "Adviser's name can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name can not contain any non-letter!")
    private String name;
    @NotBlank(message = "Department can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Department can not contain any non-letter!")
    private String department;
    private MultipartFile picture;

}
