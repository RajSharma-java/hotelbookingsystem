package com.tech_raj.controller;

import com.tech_raj.response.ApiResponse;
import com.tech_raj.response.PagedResponse;
import com.tech_raj.response.SignUpResponse;
import com.tech_raj.dto.UserDto;
import com.tech_raj.service.interfac.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // register user
    @PostMapping("register")
    public ResponseEntity<SignUpResponse> registerUser(@Valid @RequestBody UserDto userDto){
        final SignUpResponse userDto1 = userService.userRegistration(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("getAllUserWithPagination")
    public ResponseEntity<PagedResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue ="10", required = false) int pageSize,
            @RequestParam(value= "sorBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ){
         PagedResponse<UserDto> allUser = userService.getAllUser(pageNo, pageSize, sortBy, sortDir);
         return ResponseEntity.ok(allUser);
    }

    @GetMapping("getUserById/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id){
        final UserDto user = userService.getUserById(id);
        ApiResponse response= new ApiResponse("User fetch successfully", 1, user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        final UserDto dto = userService.updateUser(id, userDto);
        ApiResponse response= new ApiResponse("User data update successfully",1, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        ApiResponse response= new ApiResponse("User Deleted successfully!!",1,null);
        return ResponseEntity.ok(response);
    }
}
