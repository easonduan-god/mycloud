package com.eason.cloud.controller;

import com.eason.cloud.entity.CommonResult;
import com.eason.cloud.entity.Payment;
import com.eason.cloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther zzyy
 * @create 2019-12-27 12:16
 */
@RestController
public class OrderController
{

//    public static final String PaymentSrv_URL = "http://localhost:8001";
    public static final String PaymentSrv_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    public CommonResult create(Payment payment)
    {
        return restTemplate.postForObject(PaymentSrv_URL + "/payment/create",payment,CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable Long id)
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/"+id, CommonResult.class, id);
    }
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment/lb")
    public String lb()
    {
        ServiceInstance instances = loadBalancer.instances(discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE"));
        return restTemplate.getForObject(instances.getUri() + "/payment/lb", String.class);
    }
}