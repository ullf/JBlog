package ru.marksblog.controller;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.User;
import ru.marksblog.service.UserService;

@Service
public class UserInterfaceImpl extends VerticalLayout implements UserInterface{

    @Autowired
    private UserService userService;

    @Override
    public String loginUser(String username, String password) {
        String user=userService.loginUser(username,password);
        return user;
    }

    @Override
    public void deleteUser(String username) {
        userService.deleteByUsername(username);
    }

    @Override
    public void addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.persist(user);
    }
}
