package com.meni.server.anotations;

import com.meni.server.repo.User;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserConverter implements AttributeConverter<User, String> {

    private static final String SEPARATOR = " ";

    @Override
    public String convertToDatabaseColumn(User p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.geteMail())
                .append(SEPARATOR)
                .append(p.getLastName())
                .append(SEPARATOR)
                .append(p.getName())
                .append(SEPARATOR)
                .append(p.getPhone());
        return sb.toString();
    }

    @Override
    public User convertToEntityAttribute(String dataString) {
        String[] data = dataString.split(SEPARATOR);
        User u = new User(data[0], data[1], data[2], data[3]);
        return u;
    }

}
