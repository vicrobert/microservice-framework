package net.yangjunbo.microserviceframework.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import net.yangjunbo.microserviceframework.consumer.msgqueue.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableDiscoveryClient
@RequestMapping("/consumer")
public class AppController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MessageSender messageSender;


    @SentinelResource(value = "sayHello")
    @RequestMapping(value = "/sayHello/{str}", method = RequestMethod.GET)
    public String sayHello(@PathVariable String str) {
        return restTemplate.getForObject("https://service-provider/v1/app/echo/" + str, String.class);
    }

    @RequestMapping(value = "/getProviderConfig", method = RequestMethod.GET)
    public Boolean getProviderConfig() {
        return restTemplate.getForObject("https://service-provider/v1/sysconfig/get", Boolean.class);
    }

    @RequestMapping(value = "/sendMsg/{str}", method = RequestMethod.GET)
    public void SendMsg(@PathVariable String str) {
        messageSender.sendToAlarmChannel(str);
    }
}
