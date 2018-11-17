package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.UserInterface;
import ru.marksblog.service.UserService;

@Route("account")
public class Account extends VerticalLayout{

    @Autowired
    private UserInterface userInterface;

    public Account(){
        Label label = new Label("Delete user");
        TextField fieldUsername = new TextField("Username:");
        Button button = new Button("delete");
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                userInterface.deleteUser(fieldUsername.getValue());
            }
        });
        add(label);
        add(fieldUsername);
        add(button);
    }
}
