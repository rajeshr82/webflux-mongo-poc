package com.rajesh.poc.exception;

import java.util.Map;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);

        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            map.put("exception", ex.getClass().getSimpleName());
            map.put("custommessage", ex.getMessage());
            map.put("status", ex.getStatus().value());
            map.put("error", ex.getStatus().getReasonPhrase());

            return map;
        }

        map.put("exception", "SystemException");
        map.put("custommessage", "System Error , Check logs!");
        map.put("status", "500");
        map.put("error", " System Error ");
        return map;
    }
}