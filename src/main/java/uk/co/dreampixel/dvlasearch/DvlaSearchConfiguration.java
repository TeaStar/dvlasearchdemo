package uk.co.dreampixel.dvlasearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class DvlaSearchConfiguration {

    @Value("${dvla.search.proxy.enabled}")
    private boolean proxyEnabled;

    @Value("${dvla.search.proxy.host}")
    private String proxyServer;

    @Value("${dvla.search.proxy.port}")
    private Integer proxyPort;

    @Value("${dvla.search.proxy.user}")
    private String proxyUser;

    @Value("${dvla.search.proxy.pass}")
    private String proxyPass;

    @Bean public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        if (proxyEnabled) {

            CredentialsProvider credentialProvider = new BasicCredentialsProvider();
            credentialProvider.setCredentials(new AuthScope(proxyServer, proxyPort),
                                              new UsernamePasswordCredentials(proxyUser, proxyPass));

            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            httpClientBuilder.useSystemProperties();
            httpClientBuilder.setProxy(new HttpHost(proxyServer, proxyPort));
            httpClientBuilder.setDefaultCredentialsProvider(credentialProvider);
            httpClientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

            CloseableHttpClient client = httpClientBuilder.build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(client);
            restTemplate.setRequestFactory(requestFactory);
        }

        return restTemplate;
    }

}
