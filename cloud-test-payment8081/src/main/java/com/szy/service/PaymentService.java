package com.szy.service;

import com.szy.pojo.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") int id);
    public String hystrixTest();
    public String hystrixError();
}
