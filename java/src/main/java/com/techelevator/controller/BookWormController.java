package com.techelevator.controller;

import com.techelevator.Service.BookService;
import com.techelevator.Service.ParentService;
import com.techelevator.Service.ReadingService;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.Reading;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Array;
import java.util.List;

@RestController
@CrossOrigin
public class BookWormController {
    private ParentService parentService;
    private BookService bookService;
    private UserDao userDao;
    private ReadingService readingService;

    BookWormController(ParentService parentService, BookService bookService, UserDao userDao, ReadingService readingService){
        this.parentService = parentService;
        this.bookService = bookService;
        this.userDao = userDao;
        this.readingService = readingService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register_child", method = RequestMethod.POST)
    public void createChildAccount(@Valid @RequestBody RegisterUserDTO newUser) {
        parentService.createChildAccount(newUser.getUsername(), newUser.getPassword());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register_family_account", method = RequestMethod.POST)
    public void createFamilyAccount(@Valid @RequestBody String familyName, Principal curUser) {
        parentService.createFamilyAccount(familyName, curUser.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/add_family_member", method = RequestMethod.POST)
    public void addFamilyMember(@RequestBody String username, Principal curUser){
        parentService.addFamilyMember(username, curUser.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public void addBook(@RequestBody String Isbn, Principal curUser){
        bookService.createBook(Isbn, curUser.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/log_reading", method = RequestMethod.POST)
    public void logReading(@RequestBody Reading reading) {
        reading.setBook_id(1);
        reading.setUser_id(1);
        readingService.logReading(reading);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> userReadingList(Principal curUser) {

        return bookService.userReadingList(userDao.findIdByUsername(curUser.getName()));
    }

}
