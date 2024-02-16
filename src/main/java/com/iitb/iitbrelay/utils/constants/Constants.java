package com.iitb.iitbrelay.utils.constants;

public class Constants {

    public static final String PAYMENT_INIT_URL = "/onlinepay_test/commJsp/v2_accessPoint.jsp";

    public enum QueryParam {
        Msg("sMsg"),
        AppId("sAppId"),
        UserId("sUserId"),
        UserName("sUserName"),
        AmountDue("sAmountDue"),
        Purpose("sPurpose"),
        ReqId("sReqId"),
        Currency("sCurrency");

        private final String query;

        QueryParam(String query) {
            this.query = query;
        }

        public String getQueryParam() {
            return this.query;
        }
    }
}
