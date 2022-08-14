package com.eason.cloud.service;

import com.eason.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
