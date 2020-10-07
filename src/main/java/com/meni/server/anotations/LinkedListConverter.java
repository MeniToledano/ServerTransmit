package com.meni.server.anotations;

import com.meni.server.repo.RequestedRoute;
import com.meni.server.repo.VolunteerRoute;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.LinkedList;

@Converter
public class LinkedListConverter implements AttributeConverter<LinkedList<VolunteerRoute>, String> {

    private static final String SEPARATOR = " ";
    private static final String LINESEPARATOR = "@";

    @Override
    public String convertToDatabaseColumn(LinkedList<VolunteerRoute> routes) {

        StringBuilder sb = new StringBuilder();
        for (VolunteerRoute r : routes) {
            sb.append(r.getArrivalTime())
                    .append(SEPARATOR)
                    .append(r.getExitTime())
                    .append(SEPARATOR)
                    .append(r.getFromLocation())
                    .append(SEPARATOR)
                    .append(r.getToLocation());
        }
        return sb.toString();
    }

    @Override
    public LinkedList<VolunteerRoute> convertToEntityAttribute(String dataString) {
        String[] data = dataString.split(LINESEPARATOR);
        LinkedList<VolunteerRoute> r = new LinkedList<VolunteerRoute>();
        for (String s : data) {
            String[] info = s.split(SEPARATOR);
            r.add(new VolunteerRoute(info[0], info[1], info[2], info[3]));
        }
        return r;
    }

}
