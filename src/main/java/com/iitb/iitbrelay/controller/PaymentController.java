package com.iitb.iitbrelay.controller;

import com.iitb.iitbrelay.dto.request.InitPaymentRequest;
import com.iitb.iitbrelay.dto.request.ValidationRequest;
import com.iitb.iitbrelay.dto.response.ValidationResponse;
import com.iitb.iitbrelay.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/init")
    public MultiValueMap<String, String> initiatePayment(@RequestBody InitPaymentRequest initPaymentRequest) {
        return paymentService.initiatePaymentRequest(initPaymentRequest);
    }

    @PostMapping("/validation")
    public ValidationResponse checkValidation(@RequestBody ValidationRequest validationRequest) {
        log.info("Request recorded {}:",validationRequest);
        return paymentService.checkValidation(validationRequest);
    }

    @GetMapping("/thank-you")
    public String getImmediateResponse(@RequestParam(name = "sMsg") String sMsg,
                                       HttpServletResponse response,
                                       HttpServletRequest request) {
        log.info("Inside [thank-you]");
        log.info("request uri: {}",request.getRequestURI());
        log.info("query string: {}",request.getQueryString());
        log.info("query path info: {}",request.getPathInfo());
        log.info("sMsg: {}",sMsg);
        return sMsg;
    }
    @GetMapping("/settle")
    public String paymentSettle(@RequestParam(name = "sMsg") String sMsg,
                                HttpServletRequest request, HttpServletResponse response){
        log.info("Inside [settle]");
        log.info("request uri: {}",request.getRequestURI());
        log.info("query string: {}",request.getQueryString());
        log.info("query path info: {}",request.getPathInfo());
        log.info("Message: {}",sMsg);
        return sMsg;
    }
}
