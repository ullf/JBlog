package ru.marksblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.User;
import ru.marksblog.repository.UserRepositry;

@Service
public class UserService {

    @Autowired
    UserRepositry userRepositry;

    private UserRepositry getUserRepositry() {
        return userRepositry;
    }

    public void persist(User user){
        if (user != null){
            getUserRepositry().save(user);
        }
    }

    public void deleteByUsername(String username){
        getUserRepositry().deleteByUsername(username);
    }

    public String loginUser(String username,String password){
        User user=getUserRepositry().loginUser(username,password);
        if (user == null) {
            return "fail";
        }
        if(user != null) {
            user.setLogin(true);
            getUserRepositry().setLogin(username,password);
        }
        return "ok";
    }
}
