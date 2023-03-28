package com.jamilis.login.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class SignUpRequestDto {
    private String name;
    @NotNull
    @NotEmpty
    @Pattern(regexp="^[a-z]+@[a-z]+.[a-z]{2,6}$", message="Invalid email format. Email format must be like: aaaaaaa@undominio.algo")
    private String email;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z][0-9]{2}[a-z]{5,9}$",
            message = "Password must contain 1 uppercase, 2 digits and lowercases. Must have length between 8 and 12")
    private String password;
    private List<PhoneDto> phones;
}
