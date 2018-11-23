package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.PostInterfaceImpl;
import ru.marksblog.entity.Post;

@Route("edit")
public class Edit extends VerticalLayout{

    @Autowired
    private PostInterfaceImpl postInterface;

    public Edit(){
        super(new Menu());
        Label labelNewPost = new Label("New Post");
        TextField fieldTitle = new TextField();
        fieldTitle.setPlaceholder("post's title");
        TextArea textPost = new TextArea();
        textPost.setPlaceholder("Your text");
        textPost.setWidth("500px");
        textPost.setHeight("400px");
        Button newPost = new Button("post");
        TextField fieldDeletePost = new TextField();
        fieldDeletePost.setPlaceholder("post's title");
        Button deletePost = new Button("delete post");
        add(labelNewPost);
        add(fieldTitle);
        add(textPost);
        add(newPost);
        add(fieldDeletePost);
        add(deletePost);
        newPost.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String title = fieldTitle.getValue();
                String post = textPost.getValue();
                Post newPost = new Post();
                newPost.setTitle(title);
                newPost.setPost(post);
                postInterface.addPost(newPost);
            }
        });
        deletePost.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String title = fieldDeletePost.getValue();
                postInterface.deletePostByTitle(title);
            }
        });
    }
}
