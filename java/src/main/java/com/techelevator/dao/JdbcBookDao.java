package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Book> userReadingList(int userId){
        String sql = "SELECT b.* FROM book_user bu JOIN book b ON bu.book_id = b.book_id WHERE bu.user_id = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId);

        ArrayList<Book> books = new ArrayList<>();
        while(rows.next()){
            books.add(mapRowToBook(rows));
        }
        return books;
    }

    private Book mapRowToBook(SqlRowSet row){
        Book book = new Book();
        book.setDescription(row.getString("description"));
        book.setAuthor(row.getString("author"));
        book.setNumberofpages(row.getInt("numberofpages"));
        book.setBook_id(row.getInt("book_id"));
        book.setIsbn(row.getString("isbn"));
        book.setGenre(row.getString("genre"));
        book.setRating(row.getString("rating"));
        book.setFormat(row.getString("format"));
        book.setBook_name(row.getString("book_name"));
        return book;
    }
}
