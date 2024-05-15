package com.decportback.web.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class KISTokenDto {
    // API 문서 상 기재된 convention 대로 기재
    // response params
    private String access_token;
    private String access_token_token_expired;
    private String token_type;
    private String expires_in;
}
