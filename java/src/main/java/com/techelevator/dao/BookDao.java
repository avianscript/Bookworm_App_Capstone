package com.techelevator.dao;

import com.techelevator.model.Book;

public interface BookDao {

    void createBook(Book book, int userId);

}
