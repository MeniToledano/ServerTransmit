package com.meni.server.anotations;

import com.meni.server.repo.RequestedRoute;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RouteConverter implements AttributeConverter<RequestedRoute, String> {

    private static final String SEPARATOR = " ";


    @Override
    public String convertToDatabaseColumn(RequestedRoute r) {
        StringBuilder sb = new StringBuilder();
        sb.append(r.getArrivalTime())
                .append(SEPARATOR)
                .append(r.getExitTime())
                .append(SEPARATOR)
                .append(r.getFromLocation())
                .append(SEPARATOR)
                .append(r.getToLocation());

        return sb.toString();
    }


    @Override
    public RequestedRoute convertToEntityAttribute(String dataString) {
        String[] data = dataString.split(SEPARATOR);
        return new RequestedRoute(data[0], data[1], data[2], data[3]);
    }
}
