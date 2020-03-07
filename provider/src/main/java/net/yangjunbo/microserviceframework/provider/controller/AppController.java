package net.yangjunbo.microserviceframework.provider.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/app")
public class AppController {
    private static InetAddress addr;
    static {
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
        }
    }

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return addr.getHostName() + "-->" + string;
    }
}
