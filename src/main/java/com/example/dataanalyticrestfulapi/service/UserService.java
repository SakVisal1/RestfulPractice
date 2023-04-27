package com.example.dataanalyticrestfulapi.service;

import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;

import java.util.List;

public interface UserService {
    List<User> findUserByName();
    List<User> allUsers();
    User findUserByID(int id);


    int createNewUser(User user);

    int updateUser(User user, int id);
    int removeUser(int id);

    List<UserAccount> getAllUserAccounts();
}
