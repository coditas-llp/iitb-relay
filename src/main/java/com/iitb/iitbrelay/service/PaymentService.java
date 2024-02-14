package com.iitb.iitbrelay.service;

import com.iitb.iitbrelay.advice.OPServerException;
import com.iitb.iitbrelay.dto.request.InitPaymentRequest;
import com.iitb.iitbrelay.utils.constants.Constants;
import com.iitb.iitbrelay.utils.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    @Value("${op.referer.url}")
    private String refererUrl;
    @Value("${app.id}")
    private String appId;

    private final WebClient webClient;

    public MultiValueMap<String, String> initiatePaymentRequest(InitPaymentRequest paymentRequest) {
        log.info("[initiatePaymentRequest] to OP");
        String queryParam = buildPaymentInitiationQueryParams(paymentRequest);

        String initPaymentHtmlResponse = webClient.get()
                .uri(uriBuilder ->
                {
                    uriBuilder.path(Constants.PAYMENT_INIT_URL)
                            .queryParam(Constants.QueryParam.Msg.getQueryParam(), queryParam);
                    log.info("OP URL: {}", uriBuilder.toUriString());
                    return uriBuilder.build();
                })
                .header(HttpHeaders.REFERER, refererUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // TODO: 2/14/2024 Save response to database 
        MultiValueMap<String, String> paymentInitResponse = extractPaymentInitResponse(initPaymentHtmlResponse);
        return paymentInitResponse;
    }

    private String buildPaymentInitiationQueryParams(InitPaymentRequest paymentRequest) {
        String equals = "=";
        StringJoiner queryParams = new StringJoiner("&");
        queryParams.add(Constants.QueryParam.AppId.getQueryParam() + equals + appId);
        queryParams.add(Constants.QueryParam.UserId.getQueryParam() + equals + paymentRequest.getUserId());
        queryParams.add(Constants.QueryParam.UserName.getQueryParam() + equals + paymentRequest.getUsername());
        queryParams.add(Constants.QueryParam.AmountDue.getQueryParam() + equals + paymentRequest.getAmountDue());
        queryParams.add(Constants.QueryParam.Purpose.getQueryParam() + equals + paymentRequest.getPurpose());
        queryParams.add(Constants.QueryParam.ReqId.getQueryParam() + equals + paymentRequest.getReqId());
        queryParams.add(Constants.QueryParam.Currency.getQueryParam() + equals + paymentRequest.getCurrency());
        return queryParams.toString();
    }

    private MultiValueMap<String, String> extractPaymentInitResponse(String html) {
        if (html.toLowerCase().contains("internal server error")) {
            throw new OPServerException(ErrorCode.OPErrorCode.INTERNAL_SERVER);
        }
        if (html.toLowerCase().contains("invalid request")) {
            throw new OPServerException(ErrorCode.OPErrorCode.INVALID_REQUEST);
        }
        if (html.toLowerCase().contains("invalid access. requesting page referer not received")) {
            throw new OPServerException(ErrorCode.OPErrorCode.INVALID_REFERER);
        }

        MultiValueMap<String, String> responseMap = new LinkedMultiValueMap<>();
        Pattern pattern = Pattern.compile("<input type=\"hidden\" name=\"(.*?)\" value=\"(.*?)\">");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String name = matcher.group(1);
            String value = matcher.group(2);
            responseMap.add(name, value);
        }
        return responseMap;
    }
}
