package ru.denis.library.util.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.denis.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public final Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("person_id"));
        person.setFullName(rs.getString("person_name"));
        person.setYearOfBirth(rs.getInt("person_year_birth"));

        return person;
    }
}
