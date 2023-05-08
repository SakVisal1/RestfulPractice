package com.example.dataanalyticrestfulapi.service;

import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;
import com.example.dataanalyticrestfulapi.model.request.UserRequest;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> findUserByName();
    PageInfo<User> allUsers(int page , int size,String filterName);
    User findUserByID(int id);


    int createNewUser(UserRequest userRequest);

    int updateUser(UserRequest user, int id);
    int removeUser(int id);

    List<UserAccount> getAllUserAccounts();
}
