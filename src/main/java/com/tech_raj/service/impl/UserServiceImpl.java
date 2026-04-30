package com.tech_raj.service.impl;

import com.tech_raj.dto.LoginRequest;
import com.tech_raj.dto.LoginResponse;
import com.tech_raj.dto.SignUpResponse;
import com.tech_raj.dto.UserDto;
import com.tech_raj.entity.User;
import com.tech_raj.repo.UserRepo;
import com.tech_raj.service.interfac.UserService;
import com.tech_raj.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.LoginExceptionResolver;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public SignUpResponse userRegistration(UserDto dto) {
         Optional<User> email = userRepo.findByEmail(dto.getEmail());
         if(email.isPresent()){
             throw  new RuntimeException("Email already exists!!");
         }
         User user = mapper.map(dto, User.class);
         user.setPassword(passwordEncoder.encode(dto.getPassword()));
         User save = userRepo.save(user);
         String token = jwtUtils.generateToken(save);
         SignUpResponse response= new SignUpResponse();
         response.setStatus("Success");
         response.setMsg("User Registration successfully!!");
         response.setToken(token);
         response.setData(Collections.singletonList(mapper.map(save, UserDto.class)));
        return response;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRole()))
                );
        final String token = jwtUtils.generateToken(userDetails);
       LoginResponse loginResponse= new LoginResponse();
       loginResponse.setMsg("Login Successfully");
       loginResponse.setStatus("success");
       loginResponse.setToken(token);

        return loginResponse;
    }

    @Override
    public UserDto getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public UserDto getUserByDto(Long id) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }
}
