package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;
import com.example.dataanalyticrestfulapi.model.request.UserRequest;
import com.example.dataanalyticrestfulapi.repository.UserRepo;
import com.example.dataanalyticrestfulapi.service.UserService;
import com.example.dataanalyticrestfulapi.utils.Response;
import org.mapstruct.control.MappingControl;
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
   public Response<List<User>> getAllUsers(){
        try {
            List<User> response = userService.allUsers();
            return Response.<List<User>>ok().setPayload(response).setMessage("Successfully retrieved all users !");
        }catch (Exception exception){
            return Response.<List<User>>exception().setMessage("Failed to retrieved the users! Exception occurred!");
        }

    }
    @GetMapping("/{id}")
    public Response<User> findUserByID(@PathVariable int id){
        try{
           User response = userService.findUserByID(id);
          if(response != null){
              return Response.<User>ok().setPayload(response)
                      .setSuccess(true).setMessage("Successfully retrieved user with id : " +id);
          }else {
              return Response.<User>notFound().setMessage("User with id =" + id + "doesn't exist").setSuccess(false);
          }
       }catch(Exception exception){
            return Response.<User>exception().setMessage("Failed to retrieved user with id= " + id);
        }
    }
    @PostMapping("/new-user")
    public Response<User> createUser(@RequestBody UserRequest request){
        System.out.println("affectRow" + request);
        try {
            int userId = userService.createNewUser(request);
            System.out.println("affectRow" + userId);
          if (userId>0) {
              User response = new User().setUserId(userId).setUsername(request.getUsername())
                      .setAddress(request.getAddress())
                      .setGender(request.getGender());


              return Response.<User>updateSuccess().setPayload(response).setMessage("Create User Successfully").setSuccess(true);
          }else
              return Response.<User>notFound().setMessage("failed to create user ");

        }catch (Exception exception){
            return Response.<User>exception().setMessage("Exception occurs! Failed to create a new user").setSuccess(false);
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
    @PutMapping("/{id}")
    public Response<User> updateUserByID(@PathVariable int id,@RequestBody UserRequest request){
        try {
            int affectedRow = userService.updateUser(request,id);
            if(affectedRow>0){
                User response = new User().setUserId(id).setUsername(request.getUsername())
                        .setAddress(request.getAddress()).setGender(request.getGender());
                return Response.<User>updateSuccess().setMessage("Successfully to update users")
                        .setPayload(response).setSuccess(true);
            }else {
                return Response.<User>notFound().setMessage("Cannot update , user with id : " +id +
                        "doesn't exist ! ").setSuccess(false);
            }
        }catch (Exception e){
                return Response.<User>exception().setMessage("Failed to update user, Exception occurs !");
        }

    }

}
