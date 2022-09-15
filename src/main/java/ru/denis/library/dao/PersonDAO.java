package ru.denis.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.denis.library.models.Book;
import ru.denis.library.models.Person;
import ru.denis.library.util.mapper.BookPersonOwner;
import ru.denis.library.util.mapper.PersonMapper;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT Person.* FROM Person", new PersonMapper());
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

    public Optional<Person> getPersonByFullName(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_name=?", new PersonMapper(),
                fullName).stream().findAny();
    }

    public List<Book> listBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new BookPersonOwner(), id);
    }
}
