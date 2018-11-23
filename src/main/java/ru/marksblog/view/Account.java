package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.UserInterface;

import java.util.List;

@Route("account")
public class Account extends VerticalLayout implements HasUrlParameter<String> {

    private List<String> username;

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
        Button logout = new Button("logout");
        logout.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                userInterface.logoutUser(username.get(0));
                getUI().get().navigate("");
            }
        });
        add(label);
        add(fieldUsername);
        add(button);
        add(logout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        username = beforeEvent.getLocation().getQueryParameters().getParameters().get("username");
        if(!userInterface.findUserByUsername(username.get(0)).isLogin()) {
            beforeEvent.getUI().navigate("");
            beforeEvent.getUI().getPage().reload();
        }
    }
}
