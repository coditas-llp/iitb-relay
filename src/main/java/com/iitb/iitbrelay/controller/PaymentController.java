package com.iitb.iitbrelay.controller;

import com.iitb.iitbrelay.dto.request.InitPaymentRequest;
import com.iitb.iitbrelay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/init")
    public MultiValueMap<String, String> initiatePayment(@RequestBody InitPaymentRequest initPaymentRequest) {
        return paymentService.initiatePaymentRequest(initPaymentRequest);
    }
}
