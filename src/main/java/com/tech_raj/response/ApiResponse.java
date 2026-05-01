package com.tech_raj.response;

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
public class ApiResponse<T> {

    public String msg;
    public int status;
    public T data;
    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(msg, 200, data);
    }


    public static <T> ApiResponse<T> error(String msg, int status) {
        return new ApiResponse<>(msg, status, null);
    }

}
