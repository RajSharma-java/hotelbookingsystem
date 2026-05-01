package com.tech_raj.response;

import com.tech_raj.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private String status;
    private String msg;
    private String token;
    private UserDto data;
}
