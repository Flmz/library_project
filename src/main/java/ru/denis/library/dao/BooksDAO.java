package ru.denis.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.denis.library.models.Book;
import ru.denis.library.models.Person;
import ru.denis.library.util.mapper.BookOwnerMapper;
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

    public List<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book " +
                "JOIN person on book.person_id = person.person_id WHERE Book.book_id = ?",
                new BookOwnerMapper(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null where book_id=?", id);
    }

    public void assign(int bookId, Person selectedPerson) {
        jdbcTemplate.update("UPDATE book SET person_id=? where book_id=?", selectedPerson.getId(), bookId);
    }
}
