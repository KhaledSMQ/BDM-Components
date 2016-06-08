package integration.bigip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public abstract class BaseService {

    public static HttpHeaders DEFAULT_HTTP_HEADERS;

    static {
        DEFAULT_HTTP_HEADERS = new HttpHeaders();
        DEFAULT_HTTP_HEADERS.set(HttpHeaders.CONTENT_TYPE, "text/xml");
    }

    @Autowired
    protected RestTemplate template;

    @Autowired
    protected Environment environment;

    protected String getServiceURL(String resourceKey, String address) {
        return String.format(environment.getProperty(resourceKey),address);
    }
}
