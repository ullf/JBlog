package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.UserInterfaceImpl;
import ru.marksblog.entity.User;

@Route("")
@PageTitle("Main page")
public class Index extends VerticalLayout{

    @Autowired
    private UserInterfaceImpl userInterface;

    public Index() {
        super(new Menu());
        Label label = new Label("Registration");
        TextField fieldUsername = new TextField("username: ");
        PasswordField fieldPassword = new PasswordField("password: ");
        fieldUsername.setRequired(true);
        fieldPassword.setRequired(true);
        Button button = new Button();
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String username = fieldUsername.getValue();
                String password = fieldPassword.getValue();
                userInterface.addUser(username,password);
            }
        });
        add(label);
        button.setText("add");
        add(fieldUsername);
        add(fieldPassword);
        add(button);
    }
}
