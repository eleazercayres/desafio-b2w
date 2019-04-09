package com.b2w.desafio.infrastructure.api.pool;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.Client;
import feign.Request;
import feign.httpclient.ApacheHttpClient;

public class SwapiAPIPoolConfig {
    @Value("${pool.swapi.maxConn}")
    private Integer connPerRoute;

    @Value("${pool.swapi.maxRoutes}")
    private Integer maxConnTotal;

    @Value("${pool.swapi.connTimeout}")
    private Integer connTimeout;

    @Value("${pool.swapi.readTimeout}")
    private Integer readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connTimeout, readTimeout);
    }

    @Bean
    public Client poolConfig() {
        return new ApacheHttpClient(
                HttpClientBuilder.create()
                        .setMaxConnPerRoute(connPerRoute)
                        .setMaxConnTotal(maxConnTotal)
                        .build()
        );
    }
}
