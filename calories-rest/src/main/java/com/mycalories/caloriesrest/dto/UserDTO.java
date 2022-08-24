package com.mycalories.caloriesrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Nullable
    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
