package com.tech_raj.service.interfac;

import com.tech_raj.dto.LoginRequest;
import com.tech_raj.dto.LoginResponse;
import com.tech_raj.dto.SignUpResponse;
import com.tech_raj.dto.UserDto;

public interface UserService {
     SignUpResponse userRegistration(UserDto dto);

     LoginResponse loginUser(LoginRequest loginRequest);

     UserDto getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);

     UserDto getUserByDto(Long id);

     UserDto updateUser(Long id, UserDto dto);

     String deleteUser(Long id);
}
