package com.tech_raj.service.impl;

import com.tech_raj.dto.LoginRequest;
import com.tech_raj.exception.EmailAlreadyExistsException;
import com.tech_raj.exception.InvalidCredentialsException;
import com.tech_raj.exception.UserNotFoundException;
import com.tech_raj.response.LoginResponse;
import com.tech_raj.response.PagedResponse;
import com.tech_raj.response.SignUpResponse;
import com.tech_raj.dto.UserDto;
import com.tech_raj.entity.User;
import com.tech_raj.repo.UserRepo;
import com.tech_raj.service.interfac.UserService;
import com.tech_raj.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public SignUpResponse userRegistration(UserDto dto) {
         Optional<User> user = userRepo.findByEmail(dto.getEmail());
         if(user.isPresent()){
             throw  new EmailAlreadyExistsException("Email already exists!!");
         }
         User users = mapper.map(dto, User.class);
         users.setPassword(passwordEncoder.encode(dto.getPassword()));
         User save = userRepo.save(users);
         String token = jwtUtils.generateToken(save,user.get().getId());
         SignUpResponse response= new SignUpResponse();
         response.setStatus("Success");
         response.setMsg("User Registration successfully!!");
         response.setToken(token);
         response.setData((mapper.map(save, UserDto.class)));
        return response;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRole()))
                );
        final String token = jwtUtils.generateToken(userDetails, user.getId());
       LoginResponse loginResponse= new LoginResponse();
       loginResponse.setToken(token);

        return loginResponse;
    }

    @Override
    public PagedResponse<UserDto> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
         Page<User> pages = userRepo.findAll(pageable);
         List<UserDto> data = pages.stream()
                .map(users -> mapper.map(users, UserDto.class)).collect(Collectors.toList());
         log.info("data: " + data);
        return new PagedResponse<>(
                data,
                pages.getNumber(),
                pages.getSize(),
                pages.getTotalElements(),
                pages.getTotalPages(),
                pages.isLast()
        );
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
