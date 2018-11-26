package ru.marksblog.controller;

import org.springframework.stereotype.Repository;
import ru.marksblog.entity.User;

@Repository
public interface UserInterface {

    String loginUser(String username,String password);
    void deleteUser(String username);
    void addUser(String username,String password);
    void logoutUser(String username);
    User findUserByUsername(String username);

}
