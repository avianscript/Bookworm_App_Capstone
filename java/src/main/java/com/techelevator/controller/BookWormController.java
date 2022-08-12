package com.techelevator.controller;

import com.techelevator.Service.BookService;
import com.techelevator.Service.ParentService;
import com.techelevator.Service.ReadingService;
import com.techelevator.Service.UserService;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.ReadingActivity;
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
    private UserService userService;

    BookWormController(ParentService parentService, BookService bookService, UserDao userDao, ReadingService readingService, UserService userService){
        this.parentService = parentService;
        this.bookService = bookService;
        this.userDao = userDao;
        this.readingService = readingService;
        this.userService = userService;
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
        reading.setBook_id(bookService.getIdByIsbn(reading.getIsbn()));
        reading.setUser_id(userDao.findIdByUsername(reading.getUsername()));
        readingService.logReading(reading);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> userReadingList(Principal curUser) {

        return bookService.userReadingList(userDao.findIdByUsername(curUser.getName()));
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/update_book_status", method = RequestMethod.PUT)
    public void updateBookStatus(@RequestBody List<String> data, Principal curUser) {
        userService.updateBookStatus(userDao.findIdByUsername(curUser.getName()), bookService.getIdByIsbn(data.get(0)), data.get(1));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/check_reading_activity", method = RequestMethod.GET)
    public ReadingActivity checkReadingActivity(@RequestBody String userName) {

        int userid = userDao.findIdByUsername(userName);
        ReadingActivity readingActivity = new ReadingActivity( userService.getMinutesRead(userid), userService.getBooksByStatus(userid, "READ"),userService.getBooksByStatus(userid, "READING"));

        return readingActivity;

     }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/check_reading_activity_child", method = RequestMethod.GET)
    public ReadingActivity checkReadingActivityChild(Principal curUser) {

        int userid = userDao.findIdByUsername(curUser.getName());
        ReadingActivity readingActivity = new ReadingActivity( userService.getMinutesRead(userid), userService.getBooksByStatus(userid, "READ"),userService.getBooksByStatus(userid, "READING"));

        return readingActivity;

    }
}
