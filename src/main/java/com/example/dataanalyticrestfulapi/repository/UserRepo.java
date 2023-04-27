package com.example.dataanalyticrestfulapi.repository;

import com.example.dataanalyticrestfulapi.model.Account;
import com.example.dataanalyticrestfulapi.model.User;
import com.example.dataanalyticrestfulapi.model.UserAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepo {
    @Result(property = "userId", column = "id")
    @Select(" select * from users_tb")
    List<User> allUsers();
    List<User> findUserByUsername(String username);
    @Insert("insert into users_tb (username, gender, address)\n" +
            "values(#{user.username},#{user.gender},#{user.address})")
    int createNewUser(@Param("user") User user);
    @Update("UPDATE users_tb SET username=#{user.username}," +
            "gender=#{user.gender},address=#{user.address} " +
            "WHERE id=#{id}")
    int updateUsers(@Param("user") User user,@Param("id") int id);
    @Results({
//            @Result(column = "id", property = "userId"),
//            @Result(property = "userId", column = "id"),
            @Result(column = "id", property = "accounts", many = @Many(select = "findAccountsByUserId"))
    })
    @Select("select * from users_tb")
    List<UserAccount> getAllUserAccount();
    @Results({
            @Result(property = "accountName",column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property ="transferLimit", column = "transfer_limit"),
            @Result(property ="phoneNumber", column = "phone_number"),
            @Result(property = "accountType", column = "account_type",
                    one = @One(select = "com.example.dataanalyticrestfulapi.repository.AccountRepo.getAccountTypeByID"))
    })
    @Select("select * from useraccount_tb " +
            "    inner join account_tb " +
            "        a on a.id = useraccount_tb.account_id\n" +
            "    where user_id = #{id};")
    List<Account> findAccountsByUserId(int id);
    @Result(property = "userId", column = "id")
    @Select("select  * from users_tb where id = #{id}")
    User findUserByID(int id );


    @Select("SELECT * FROM users_tb WHERE id=#{id}")
    User findUserById(int id);

    @Delete("DELETE FROM users_tb WHERE id=#{id}")
    int removeUser(@Param("id") Integer id);
}
