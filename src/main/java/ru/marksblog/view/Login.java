package ru.marksblog.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.UserInterfaceImpl;
import ru.marksblog.entity.User;
import ru.marksblog.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Route("login")
@PageTitle("Login")
public class Login extends VerticalLayout {

    @Autowired
    private UserInterfaceImpl userInterface;

    public Login(){
        super(new Menu());
        Label label = new Label("Login");
        TextField fieldUsername = new TextField("username: ");
        PasswordField fieldPassword = new PasswordField("password: ");
        fieldUsername.setRequired(true);
        fieldPassword.setRequired(true);
        Button button = new Button();
        button.setText("login");
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String username = fieldUsername.getValue();
                String password = fieldPassword.getValue();
                String user = userInterface.loginUser(username,password);
                if (user.equals("fail")) {
                    getUI().get().navigate("fail");
                } else {
                    HashMap<String,String> map= new HashMap<>();
                    map.put("username",username);
                    HttpServletRequest request = (HttpServletRequest) VaadinRequest.getCurrent();
                    request.getSession().setAttribute("username",username);
                    getUI().get().navigate("account",QueryParameters.simple(map));
                }
            }
        });
        add(label);
        add(fieldUsername);
        add(fieldPassword);
        add(button);
    }

}
