package com.eason.cloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// name为服务名
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyLoadBalanceConfiguration.class)
public class MyLoadBalanceConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule(); // 采用随机策略
    }
}