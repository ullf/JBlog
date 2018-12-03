package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.UserInterface;
import javax.servlet.http.HttpServletRequest;

@Route("account")
public class Account extends VerticalLayout implements HasUrlParameter<String> {

    @Autowired
    private UserInterface userInterface;

    public Account(){
        super(new Menu());
        Label label = new Label("Delete user");
        TextField fieldUsername = new TextField("Username:");
        Button button = new Button("delete");
        Button buttonEdit = new Button("new post");
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
                HttpServletRequest request = (HttpServletRequest) VaadinRequest.getCurrent();
                userInterface.logoutUser(request.getSession().getAttribute("username").toString());
                getUI().get().navigate("");
            }
        });
        Registration registration = buttonEdit.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                HttpServletRequest request = (HttpServletRequest) VaadinRequest.getCurrent();
                request.getSession().setAttribute("username",request.getSession().getAttribute("username").toString());
                getUI().get().navigate("edit");
            }
        });
        add(label);
        add(fieldUsername);
        add(button);
        add(buttonEdit);
        add(logout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        HttpServletRequest request = (HttpServletRequest) VaadinRequest.getCurrent();
        if(request.getSession().getAttribute("username") == null){
            beforeEvent.getUI().navigate("");
            beforeEvent.getUI().getPage().reload();
        } else {
            String username = request.getSession().getAttribute("username").toString();
            if(!userInterface.findUserByUsername(username).isLogin()) {
                beforeEvent.getUI().navigate("");
                beforeEvent.getUI().getPage().reload();
            }
        }
    }
}
