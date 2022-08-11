package com.techelevator.controller;

import com.techelevator.Service.BookService;
import com.techelevator.Service.ParentService;
import com.techelevator.dao.UserDao;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class BookWormController {
    private ParentService parentService;
    private BookService bookService;
    private UserDao userDao;

    BookWormController(ParentService parentService, BookService bookService, UserDao userDao){
        this.parentService = parentService;
        this.bookService = bookService;
        this.userDao = userDao;
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

}
