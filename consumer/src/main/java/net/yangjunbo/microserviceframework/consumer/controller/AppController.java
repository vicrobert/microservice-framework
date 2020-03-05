package net.yangjunbo.microserviceframework.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("https://service-provider/v1/app/echo/" + str, String.class);
    }

    @RequestMapping(value = "/getProviderConfig", method = RequestMethod.GET)
    public Boolean getProviderConfig() {
        return restTemplate.getForObject("https://service-provider/v1/sysconfig/get", Boolean.class);
    }
}
