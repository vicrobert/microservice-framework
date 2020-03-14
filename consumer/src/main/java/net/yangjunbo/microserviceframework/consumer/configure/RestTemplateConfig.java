package net.yangjunbo.microserviceframework.consumer.configure;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import net.yangjunbo.microserviceframework.consumer.utils.ExceptionUtil;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

/**
 * RestTemplate 配置类
 * author: yangjb
 * date: 3/6/2020
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 配置Https SSL支持
     * @return
     * @throws Exception
     */
    @LoadBalanced
    @Bean
    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    RestTemplate restTemplate() throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        ClassPathResource resource = new ClassPathResource("cert.p12");
        InputStream inputStream = resource.getInputStream();
        keyStore.load(inputStream, "abc123".toCharArray());

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(keyStore, acceptingTrustStrategy)
                .build();

        //NoopHostnameVerifier.INSTANCE 不验证证书中的主机域名
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }
}
