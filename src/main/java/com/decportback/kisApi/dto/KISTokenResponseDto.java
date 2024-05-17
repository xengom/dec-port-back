package com.decportback.kisApi.dto;

import lombok.*;

@Getter
public class KISTokenResponseDto {
    // API 문서 상 기재된 convention 대로 기재
    // response params
    private String access_token;
    private String access_token_token_expired;
    private String token_type;
    private Number expires_in;

    // local test용 Builder
    @Builder
    // TODO Local Test 로직 걷어내기
    public KISTokenResponseDto(String access_token, String access_token_token_expired, String token_type, Number expires_in) {
        this.access_token = access_token;
        this.access_token_token_expired = access_token_token_expired;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }
}
