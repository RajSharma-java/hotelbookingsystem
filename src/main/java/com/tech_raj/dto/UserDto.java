package com.tech_raj.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tech_raj.entity.Booking;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;

    @NotBlank(message = "Email should be required!!")
    private String email;

    @NotBlank(message = "Name should be required!!")
    private String name;

    @NotBlank(message = "Phone Number should be required!!")
    private String phoneNumber;

    @NotBlank(message = "Password should be required!!")
    private String password;
    private String role="ROLE_USER";
    private List<BookingDto> booking=new ArrayList<>();

}
