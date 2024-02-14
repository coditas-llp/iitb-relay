package com.iitb.iitbrelay.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Errors {
    private Integer errorCode;
    private String message;
}
