package com.iitb.iitbrelay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitPaymentRequest {
    private String userId;
    private String username;
    private String amountDue;
    private String purpose;
    private String reqId;
    private String currency;
}
