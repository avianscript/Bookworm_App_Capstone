package com.techelevator.Service;

import com.techelevator.dao.UserDao;
import org.springframework.stereotype.Component;

@Component
public class ParentService {
    private UserDao userDao;
    public ParentService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createChildAccount(String username, String password) {
        userDao.create(username, password, "ROLE_USER");
    }

    public void createFamilyAccount() {

    }
}
