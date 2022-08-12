package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcBookDao implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createBook(Book book, int userId){
        String sql = "INSERT INTO book (book_name, isbn, description, author, numberofpages, rating, genre) values (?, ?, ?, ?, ?, ?,?) RETURNING book_id";

        Integer bookId;
        try {
            bookId = jdbcTemplate.queryForObject(sql, Integer.class, book.getBook_name(), book.getIsbn(), book.getDescription(), book.getAuthor(), book.getNumberofpages(), book.getRating(), book.getGenre());
        } catch (DataAccessException e) {
            return;
        }

        sql = "INSERT INTO book_user (book_id, user_id) values (?, ?)";
        try {
            jdbcTemplate.update(sql, bookId, userId);
        } catch (DataAccessException e) {
            return;
        }
    }
}
