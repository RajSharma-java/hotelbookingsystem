package com.tech_raj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private String status;
    private String msg;
    private String token;
    private List<UserDto> data;
}
