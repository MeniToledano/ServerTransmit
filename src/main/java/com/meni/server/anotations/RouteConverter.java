package com.meni.server.anotations;

import com.meni.server.repo.Person;
import com.meni.server.repo.Route;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RouteConverter  implements AttributeConverter<Route, String> {

    private static final String SEPARATOR = " ";

    /**
     * Convert Color object to a String
     * with format red|green|blue|alpha
     */
    @Override
    public String convertToDatabaseColumn(Route r) {
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

    /**
     * Convert a String with format red|green|blue|alpha
     * to a Color object
     */
    @Override
    public Route convertToEntityAttribute(String dataString) {
        String[] data = dataString.split(SEPARATOR);
        return new Route(data[0], data[1], data[2], data[3]);
    }
}
