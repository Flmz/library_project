package ru.denis.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.denis.library.models.Book;
import ru.denis.library.models.Person;
import ru.denis.library.util.mapper.BookListPeopleMapper;
import ru.denis.library.util.mapper.BookMapper;

import java.util.List;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE book_id=?"
                , new BookMapper(), id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_name, book_author,book_year_production) " +
                "VALUES(?,?,?)", book.getBookName(), book.getBookAuthor(), book.getYearOfProduction());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET book_name=?,book_author=?, book_year_production=?" +
                        "WHERE book_id=?", updatedBook.getBookName(), updatedBook.getBookAuthor()
                , updatedBook.getYearOfProduction(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public List<Person> bookList(int id) {
        return jdbcTemplate.query("SELECT person_name FROM Book " +
                "JOIN person on person.person_id = book.person_id WHERE book_id = ?", new BookListPeopleMapper(), id);
    }
}
