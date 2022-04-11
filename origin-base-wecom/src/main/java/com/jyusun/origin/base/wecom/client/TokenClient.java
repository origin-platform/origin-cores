package com.jyusun.origin.base.wecom.client;

import com.jyusun.origin.base.wecom.common.constant.WecomUrlConstant;
import com.jyusun.origin.base.wecom.common.result.WocomResult;
import com.jyusun.origin.base.wecom.config.props.WecomProperties;
import com.jyusun.origin.base.wecom.model.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class TokenClient {

    private final RestTemplate restTemplate;
    private final WecomProperties wecomProperties;

    public String endpoint() {
        return wecomProperties.getEndpoint();
    }

    public String token() {

        WocomResult<TokenDTO> result = restTemplate.getForObject(this.endpoint() + WecomUrlConstant.GET_TOKEN,
                WocomResult.class);
        return result.getData().getAccessToken();
    }

}
