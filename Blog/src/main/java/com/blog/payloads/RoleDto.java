package com.blog.payloads;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleDto {
    private Integer id;
    private String name;
}
