package ru.marksblog.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.PostInterfaceImpl;
import ru.marksblog.controller.UserInterface;
import ru.marksblog.entity.Post;
import ru.marksblog.entity.Theme;
import ru.marksblog.repository.ThemeRepository;

import javax.servlet.http.HttpServletRequest;

@Route("edit")
public class Edit extends VerticalLayout  implements HasUrlParameter<String> {

    @Autowired
    private PostInterfaceImpl postInterface;

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private ThemeRepository themeRepository;

    public Edit(){
        super(new Menu());
        Label labelNewPost = new Label("New Post");

        TextField fieldTheme = new TextField();
        fieldTheme.setPlaceholder("Theme");
        Button buttonTheme = new Button("add theme");
        buttonTheme.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String theme = fieldTheme.getValue();
                Theme newTheme = new Theme();
                newTheme.setThemename(theme);
                themeRepository.save(newTheme);
            }
        });

        TextField fieldChooseTmene = new TextField();
        fieldChooseTmene.setPlaceholder("Theme");
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
        add(fieldTheme);
        add(buttonTheme);
        add(fieldChooseTmene);
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
                String theme =fieldChooseTmene.getValue();
                Post newPost = new Post();
                if(themeRepository.getPostsByTheme(theme) != null){
                    newPost.setThemename(theme);
                }
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

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        HttpServletRequest request = (HttpServletRequest) VaadinRequest.getCurrent();
        if(request.getSession().getAttribute("username") == null){
            beforeEvent.getUI().navigate("");
            beforeEvent.getUI().getPage().reload();
        } else {
            String username = request.getSession().getAttribute("username").toString();
            if(userInterface.findUserByUsername(username) == null || !userInterface.findUserByUsername(username).isLogin()){
                beforeEvent.getUI().navigate("");
                beforeEvent.getUI().getPage().reload();
            }
        }
    }
}
