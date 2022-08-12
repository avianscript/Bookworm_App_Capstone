package com.techelevator.Service;

import com.techelevator.dao.UserDao;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updateBookStatus(int userId, int bookId, String status) {
        userDao.updateBookStatus(userId, bookId, status);
    }
}
