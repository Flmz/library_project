package ru.denis.library.util.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.denis.library.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPersonOwner implements RowMapper<Book> {

    //TODO сделать нормальное название класса

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();

        book.setBookName(rs.getString("book_author"));
        book.setBookAuthor(rs.getString("book_name"));
        book.setYearOfProduction(rs.getInt("book_year_production"));
        return book;
    }
}
