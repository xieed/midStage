package com.example.nacosclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class NacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class, args);
    }

    //实例化 RestTemplate 实例
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }


    @RestController
    class EchoController {
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            return string;
        }
    }

    @RestController
    class NacosController {

        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Autowired
        private RestTemplate restTemplate;

        @Value("${spring.application.name}")
        private String appName;

        @GetMapping("/echo/app-name")
        public String echoAppName() {
            //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-producer");
            String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
            log.info("request url#{}", url);
            return restTemplate.getForObject(url, String.class);
        }

        @GetMapping("/user/login/{userName}/{pwd}")
        public String login(@PathVariable String userName, @PathVariable String pwd) {
            //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
            ServiceInstance serviceInstance = loadBalancerClient.choose("user_server");
            String url = String.format("http://%s:%s/user/login/%s/%s", serviceInstance.getHost(),
                    serviceInstance.getPort(),
                    userName, pwd);
            log.info("request url#{}", url);
            return restTemplate.getForObject(url, String.class);
        }

        @PostMapping("/test")
        public String test(@RequestBody TestData r) {
            log.info(r.toString());
            return r.toString();
        }

        @PostMapping("/user/register")
        public String register(@RequestParam String userName, @RequestParam String pwd) {
            //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
            ServiceInstance serviceInstance = loadBalancerClient.choose("user_server");
            String url = String.format("http://%s:%s/user/login/%s/%s", serviceInstance.getHost(),
                    serviceInstance.getPort(),
                    userName, pwd);
            log.info("request url#{}", url);
            return restTemplate.getForObject(url, String.class);
        }

    }


}
