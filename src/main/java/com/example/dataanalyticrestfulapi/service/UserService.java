package com.example.dataanalyticrestfulapi.service;

import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;
import com.example.dataanalyticrestfulapi.model.request.UserRequest;

import java.util.List;

public interface UserService {
    List<User> findUserByName();
    List<User> allUsers();
    User findUserByID(int id);


    int createNewUser(UserRequest userRequest);

    int updateUser(UserRequest user, int id);
    int removeUser(int id);

    List<UserAccount> getAllUserAccounts();
}
