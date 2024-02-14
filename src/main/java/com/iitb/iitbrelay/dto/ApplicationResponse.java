package com.iitb.iitbrelay.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse<T> {

    private T data;
    private List<Errors> errors;

    public static <T> ApplicationResponse<T> of(T data) {
        ApplicationResponse<T> applicationResponse = new ApplicationResponse<>();
        applicationResponse.setData(data);
        return applicationResponse;
    }

    public static <T> ApplicationResponse<T> of(T data, List<Errors> errors) {
        ApplicationResponse<T> applicationResponse = new ApplicationResponse<>();
        applicationResponse.setData(data);
        applicationResponse.setErrors(errors);
        return applicationResponse;
    }
}
