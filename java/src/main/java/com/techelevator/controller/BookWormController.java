package com.techelevator.controller;

import com.techelevator.Service.ParentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookWormController {
    private ParentService parentService;

    BookWormController(ParentService parentService){
        this.parentService = parentService;
    }
}
