package com.decportback.kisApi.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KISTokenRequestDto {
    // API 문서 상 기재된 convention 대로 기재
    // request params
    private String grant_type;
    private String appkey;
    private String appsecret;

    @Builder
    public KISTokenRequestDto(String grant_type, String appkey, String appsecret) {
        this.grant_type = grant_type;
        this.appkey = appkey;
        this.appsecret = appsecret;
    }
}
