package com.mycalories.caloriesrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class UserDTO {

    @Nullable
    private Long id;

    private String userName;

    private String email;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

}
