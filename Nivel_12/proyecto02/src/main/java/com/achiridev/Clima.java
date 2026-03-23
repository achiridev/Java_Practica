package com.achiridev;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.clima")
public class Clima {
    private String url;
    private int timeout;

    public Clima() {
    }
    public Clima(String url, int timeout) {
        this.url = url;
        this.timeout = timeout;
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public int getTimeout() { return timeout; }
    public void setTimeout(int timeout) { this.timeout = timeout; }
}
