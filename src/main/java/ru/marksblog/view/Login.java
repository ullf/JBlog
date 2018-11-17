package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.UserInterfaceImpl;
import ru.marksblog.service.UserService;

@Route("login")
@PageTitle("Login")
public class Login extends VerticalLayout{

    @Autowired
    private UserInterfaceImpl userInterface;

    @Autowired
    private UserService userService;

    public Login(){
        super(new Menu());
        Label label = new Label("Login");
        TextField fieldUsername = new TextField("username: ");
        PasswordField filedPassword = new PasswordField("password: ");
        fieldUsername.setRequired(true);
        filedPassword.setRequired(true);
        Button button = new Button();
        button.setText("login");
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String username = fieldUsername.getValue();
                String password = filedPassword.getValue();
                String user = userInterface.loginUser(username,password);
                if (user.equals("fail")) {
                    getUI().get().navigate("fail");
                } else {
                    getUI().get().navigate("account");
                }
            }
        });
        add(label);
        add(fieldUsername);
        add(filedPassword);
        add(button);
    }
}
