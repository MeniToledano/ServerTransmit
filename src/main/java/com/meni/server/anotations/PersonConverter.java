package com.meni.server.anotations;

import com.meni.server.repo.Person;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PersonConverter implements AttributeConverter<Person, String> {

    private static final String SEPARATOR = " ";

    /**
     * Convert Color object to a String
     * with format red|green|blue|alpha
     */
    @Override
    public String convertToDatabaseColumn(Person p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getAdId()).append(SEPARATOR)
                .append(p.geteMail())
                .append(SEPARATOR)
                .append(p.getLastName())
                .append(SEPARATOR)
                .append(p.getName())
                .append(SEPARATOR)
                .append(p.getPhone());
        return sb.toString();
    }

    /**
     * Convert a String with format red|green|blue|alpha
     * to a Color object
     */
    @Override
    public Person convertToEntityAttribute(String dataString) {
        String[] data = dataString.split(SEPARATOR);
        return new Person(data[1], data[2], data[3], data[4], Long.parseLong(data[0]));
    }

}
