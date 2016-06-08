package spring.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class WebConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(getBigIpDefaultHttpClient());
        clientHttpRequestFactory.setConnectTimeout(Integer.valueOf(environment.getProperty("connection.timeout")));
        clientHttpRequestFactory.setReadTimeout(Integer.valueOf(environment.getProperty("read.timeout")));
        RestTemplate template = new RestTemplate(clientHttpRequestFactory);
        return template;
    }

    @Bean
    public HttpClient getBigIpDefaultHttpClient() {
        try {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(null, -1),
                    new UsernamePasswordCredentials(environment.getProperty("bigip.ws.username"),
                            environment.getProperty("bigip.ws.password")));
            HttpClientBuilder clientBuilder = HttpClients.custom().setDefaultCredentialsProvider(credsProvider);
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new NoopHostnameVerifier());
            clientBuilder.setSSLSocketFactory(sslsf);
            HttpClient client = clientBuilder.build();
            return client;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Fail to setup http client",e);
        } catch (KeyManagementException e) {
            LOGGER.error("Fail to setup http client", e);
        } catch (KeyStoreException e) {
            LOGGER.error("Fail to setup http client", e);
        }

        return null;
    }

}
