package com.blog.entities;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {

    @NotEmpty(message = "Username must be filled")
    private String username;

    @NotEmpty(message = "Password must be filled")
    private String password;

}
