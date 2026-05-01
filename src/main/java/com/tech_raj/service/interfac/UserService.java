package com.tech_raj.service.interfac;

import com.tech_raj.dto.LoginRequest;
import com.tech_raj.response.LoginResponse;
import com.tech_raj.response.PagedResponse;
import com.tech_raj.response.SignUpResponse;
import com.tech_raj.dto.UserDto;

public interface UserService {
     SignUpResponse userRegistration(UserDto dto);

     LoginResponse loginUser(LoginRequest loginRequest);

     PagedResponse<UserDto> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);

     UserDto getUserById(Long id);

     UserDto updateUser(Long id, UserDto dto);

     String deleteUser(Long id);
}
