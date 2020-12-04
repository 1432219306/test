package com.szy.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.szy.dao.PaymentMapper;
import com.szy.pojo.Payment;
import com.szy.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    public int create(Payment payment){

        return  paymentMapper.create(payment);
    };
    public Payment getPaymentById(@Param("id") int id){
        return  paymentMapper.getPaymentById(id);
    };

@HystrixCommand(fallbackMethod = "hystrixError",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
public String hystrixTest(){
    try{
        TimeUnit.SECONDS.sleep(5);
    }
    catch (Exception e){

    };
    return ("xxx成功");
}
public String hystrixError(){
    return ("失败xxxx");
}
}