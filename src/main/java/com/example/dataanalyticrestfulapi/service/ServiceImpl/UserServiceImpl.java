package com.example.dataanalyticrestfulapi.service.ServiceImpl;

import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;
import com.example.dataanalyticrestfulapi.model.request.UserRequest;
import com.example.dataanalyticrestfulapi.repository.UserRepo;
import com.example.dataanalyticrestfulapi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public List<User> findUserByName() {
        return null;
    }

    @Override
    public PageInfo<User> allUsers(int page ,int size,String filterName) {
        // page helper is here
        PageHelper.startPage(page,size);
        return  new PageInfo<>(userRepo.allUsers(filterName));
    }

    @Override
    public User findUserByID(int id) {
        return userRepo.findUserByID(id);
    }

    @Override
    public int createNewUser(UserRequest userRequest) {

        return userRepo.createNewUser(userRequest);
    }

    @Override
    public int updateUser(UserRequest user, int id) {
        return userRepo.updateUsers( user,id);
    }

    @Override
    public int removeUser(int id) {

        return userRepo.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userRepo.getAllUserAccount();
    }
}
