package com.b2w.desafio.infrastructure.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.b2w.desafio.domain.model.external.PlanetSwapi;
import com.b2w.desafio.infrastructure.api.pool.SwapiAPIPoolConfig;
import com.b2w.desafio.infrastructure.exception.IntegrationSwapiAPIException;
import com.b2w.desafio.infrastructure.exception.SwapiAPIException;

import feign.Response;
import feign.codec.ErrorDecoder;

@FeignClient(value = "swapi-api", url = "${application.swapi.url}", configuration = {
        SwapiAPI.SwapiAPIDecoder.class, SwapiAPIPoolConfig.class
})
public interface SwapiAPI {

    @GetMapping("/planets/{planetId}")
    PlanetSwapi find(@PathVariable("planetId") Long planetId);

    class SwapiAPIDecoder implements ErrorDecoder {

        @Override
        public IntegrationSwapiAPIException decode(String methodKey, Response response) {

            final HttpStatus statusCode = HttpStatus.valueOf(response.status());

            final String message = String.format("Falha de integracao com o SwapiAPI: method: %s, httpStatus: %d",
                    methodKey, statusCode.value());

            throw new SwapiAPIException(message, statusCode);
        }
    }
}
