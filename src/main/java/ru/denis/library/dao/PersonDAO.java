package ru.denis.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.denis.library.models.Book;
import ru.denis.library.models.Person;
import ru.denis.library.util.mapper.PeopleListBookMapper;
import ru.denis.library.util.mapper.PersonMapper;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE person_id=?"
                , new PersonMapper(), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(person_name, person_year_birth) " +
                        "VALUES(?,?)", person.getFullName(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET person_name=?,person_year_birth=? WHERE person_id=?", updatedPerson.getFullName()
                , updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public List<Book> personBooks(int id) {
        return jdbcTemplate.query("select book_year_production,book_author,book_name " +
                "from person join book b on person.person_id = b.person_id " +
                "where person.person_id = ?;", new PeopleListBookMapper(), id);
    }
}
