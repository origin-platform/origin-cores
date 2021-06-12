//package com.jyusun.origin.core.tools.lock;
//
//import com.jyusun.origin.core.common.result.AbstractResult;
//import com.jyusun.origin.core.common.result.ResultFactory;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
///**
// * 获取token
// */
//@RestController
//@RequestMapping("tokens")
//@AllArgsConstructor
//public class RedisToken {
//
//    private final RedisUtil redisUtil;
//
//    /**
//     * 失效时间
//     */
//    private static final long TOKEN_TIME_OUT = 60L * 15L;
//
//    @GetMapping
//    public AbstractResult<String> getToken() {
//        String token = "usertoken" + UUID.randomUUID().toString();
//        redisUtil.set(token, token, TOKEN_TIME_OUT);
//        return ResultFactory.data(token);
//    }
//}
