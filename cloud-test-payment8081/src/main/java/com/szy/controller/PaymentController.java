package com.szy.controller;

import com.netflix.appinfo.InstanceInfo;

import com.szy.pojo.CommonResult;
import com.szy.pojo.Payment;
import com.szy.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private DiscoveryClient discoveryClient;
@Resource
    private PaymentService paymentService;
@Value(value = "${server.port}")
private String serverport;
@PostMapping(value = "/payment/create")
public CommonResult creat(@RequestBody Payment payment){
    int result=paymentService.create(payment);
    log.debug("****插入结果"+result);
    if(result>0){
        return new CommonResult(200,"插入成功"+"serverport:"+serverport,result);
    }else {
        return new CommonResult(444,"插入失败",null);
    }
}
    @PostMapping(value = "/payment/get/{id}")
    public CommonResult get(@PathVariable("id") int id){
        Payment result=paymentService.getPaymentById(id);
        log.debug("****插入结果"+result);
        if(result!=null){
            return new CommonResult(200,"查询成功"+"serverport:"+serverport,result);
        }else {
            return new CommonResult(444,"没有记录"+id,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
    List<String> services=discoveryClient.getServices();
        for (String service:services
             ) {
            log.info("输出******service:"+service);
        }
      List<ServiceInstance> instanses= discoveryClient.getInstances("PAYMENT");
      for(ServiceInstance instanse:instanses){
      log.info("输出********"+instanse);}
      return this.discoveryClient;
    }
    @GetMapping("/hystrix/get")
    public String hystrixString(){
    return paymentService.hystrixTest();
    }
}
