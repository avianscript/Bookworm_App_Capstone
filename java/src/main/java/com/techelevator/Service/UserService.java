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

    public int getBooksByStatus(int userId, String status){
        return userDao.getBooksByStatus(userId, status);

    }

    public int getMinutesRead(int userId){
        return userDao.getMinutesRead(userId);
    }
}
