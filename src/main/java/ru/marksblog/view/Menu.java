package ru.marksblog.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("menu")
public class Menu extends VerticalLayout{

    public Menu(){
        Div menu = new Div();
        menu.add(new RouterLink("Index ", Index.class));
        menu.add(new RouterLink("Login ", Login.class));
        menu.add(new RouterLink("Blog ",Blog.class));
        menu.add(new RouterLink("Account ",Account.class));
        add(menu);
    }

}
