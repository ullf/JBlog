package ru.marksblog.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("fail")
public class Fail extends VerticalLayout {

    public Fail(){
        super(new Menu());
    }
}
