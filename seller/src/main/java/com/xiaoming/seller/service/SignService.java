package com.xiaoming.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名服务
 */
@Service
public class SignService {
    static Map<String, String> PUBLIC_KEYS = new HashMap<>();
    static {
        PUBLIC_KEYS.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdLIh15vZnXlbKVzVjVKQNZ/+q\n" +
                "5uOzssX5VO1owcBsdBzVXRLPDypWrXs60xjqYYXzxWdYiuG6IrXePbnhHDpyRkrS\n" +
                "fFB/0qg5SFpqp+TFP68T/su1+2Q0SrJctRGGLU/Kk7i1+5uGsr6aQwsEymiA6z++\n" +
                "dAsnzVKKuDTTa1nDbwIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId){
        return PUBLIC_KEYS.get(authId);
    }
}
