package com.sau.dims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviserDTO {

    private int id;
    private String name;
    private String department;
    private String picture;

}
