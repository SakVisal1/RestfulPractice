package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;
import com.example.dataanalyticrestfulapi.service.UserService;
import com.example.dataanalyticrestfulapi.utils.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

   private final UserService userService;
    UserRestController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/allUsers")
    List<User> getAllUsers(){
        return userService.allUsers();
    }
    @GetMapping("/{id}")
    public User findUserByID(@PathVariable int id){
        return userService.findUserByID(id);
    }
    @PostMapping("/new-user")
    public String createUser(@RequestBody User user){
        System.out.println("affectRow" + user);
        try {
            int affectRow = userService.createNewUser(user);
            System.out.println("affectRow" + affectRow);
          if (affectRow>0)
              return "create user successfully !";
          else
              return "Cannot created a new user! ";

        }catch (Exception exception){
            return exception.getMessage();
        }
    }

    @GetMapping("/user-account")
    public Response<List<UserAccount>> getAllUserAccounts(){
        try {
            List<UserAccount> data = userService.getAllUserAccounts();
            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("Successfully retrieved all user account !");

        }catch (Exception exception){
            System.out.println("Error getting all account : "+exception.getMessage());
            return Response.<List<UserAccount>>exception().setMessage("Exception occurs ! Failed to retrieved all users accounts! ").setSuccess(false);
        }
    }
    @DeleteMapping("/remove/{id}")
    Response<User> removeUser(@PathVariable("id")Integer id){
        try {
            int result= userService.removeUser(id); ;
            if(result>0) {
                return Response.<User>deleteSuccess().setMessage("Delete Successfully .");
            }else {
                return Response.<User>ok().setMessage("Not found user by id :"+id);
            }
        }catch (Exception e){
            System.out.println("this is error :"+e);
            return Response.<User>exception().setMessage("Remove Values is Failed.").setSuccess(false);
        }

    }
    @GetMapping("/update/{id}")
    Response<User> updateUser(@RequestBody User user,@PathVariable("id") int id){
        try {
            int result= userService.updateUser(user,id);
            if(result>0) {
                return Response.<User>updateSuccess().setPayload(user).setMessage("You Are Updated Successfully.");
            }else {
                return Response.<User>ok().setMessage("Not found User by id :"+id);
            }
        }catch (Exception e){
            System.out.println("this is error :"+e);
            return Response.<User>exception().setMessage("Update Value Fail.").setSuccess(false);
        }
    }
}
