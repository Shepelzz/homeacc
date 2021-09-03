package com.homeacc.repository;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.homeacc.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

//    @Query("SELECT u FROM User u "
//        + "WHERE u.username = \":username\" AND u.password = \":password\"")
//    User findByCredentials(
//        @Param("username") String username,
//        @Param("password") String password);


    User findByUsernameAndPassword(String username, String password);

}
