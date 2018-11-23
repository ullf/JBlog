package ru.marksblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.User;
import ru.marksblog.repository.UserRepositry;

@Service
public class UserService {

    @Autowired
    private UserRepositry userRepositry;

    public void persist(User user){
        if (user != null){
            userRepositry.save(user);
        }
    }

    public void deleteByUsername(String username){
        userRepositry.deleteByUsername(username);
    }

    public String loginUser(String username,String password){
        User user = userRepositry.loginUser(username,password);
        if (user == null) {
            return "fail";
        }
        if(user != null) {
            user.setLogin(true);
            userRepositry.setLogin(username,password);
        }
        return "ok";
    }

    public User findUserByUsername(String username){
        User user = userRepositry.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        return user;
    }
}
