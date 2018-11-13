package ru.marksblog.controller;

import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface {

    void loginUser(String username,String password);
    void deleteUser(String username);
    void addUser(String username,String password);
}
