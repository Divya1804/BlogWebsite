package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {

    @Size(min = 8, max = 16, message = "Password must be grater than 8 and less than 16.")
    private String password;

    @Size(min = 8, max = 16, message = "Password must be grater than 8 and less than 16.")
    private String newPassword;

    @Size(min = 8, max = 16, message = "Password must be grater than 8 and less than 16.")
    private String confirmPassword;

}
