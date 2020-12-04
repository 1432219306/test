package com.szy.dao;

import com.szy.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentMapper {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") int id);
}
