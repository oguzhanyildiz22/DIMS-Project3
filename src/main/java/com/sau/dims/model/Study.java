package com.sau.dims.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Study's title can not be null!")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Title can not contain any non-letter!")
    private String title;
    @NotBlank(message = "Description can not be null!")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Description can not contain any non-letter!")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "study",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<AdviserStudy> adviserStudies = new ArrayList<>();
}
