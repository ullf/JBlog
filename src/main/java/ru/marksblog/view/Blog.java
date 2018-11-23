package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.PostInterfaceImpl;
import ru.marksblog.entity.Post;
import java.util.Iterator;

@Route("Blog")
public class Blog extends VerticalLayout{

    @Autowired
    private PostInterfaceImpl postInterface;

    public Blog(){
            Button show = new Button("show");
            add(show);
            show.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                @Override
                public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                    Iterator<Post> iter = postInterface.findAll();
                    while(iter.hasNext()){
                        TextArea areaPost = new TextArea();
                        areaPost.setValue(iter.next().getPost());
                        add(areaPost);
                    }
                }
            });
    }
}
