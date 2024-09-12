package com.foodie.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("foodie.jwt")
@Component //使此类成为spring容器的一个Bean
public class JwtProperties {

    //管理端管理员生成jwt令牌相关配置属性
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

}
