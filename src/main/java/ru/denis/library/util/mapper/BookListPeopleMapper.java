package ru.denis.library.util.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.denis.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookListPeopleMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Person person = new Person();

        person.setFullName(rs.getString("person_name"));

        return person;
    }
}
