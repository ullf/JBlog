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
import org.springframework.web.context.annotation.SessionScope;
import ru.marksblog.controller.PostInterfaceImpl;
import ru.marksblog.controller.ThemeInterface;
import ru.marksblog.controller.UserInterface;
import ru.marksblog.entity.Post;
import ru.marksblog.entity.Theme;
import ru.marksblog.repository.ThemeRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

@Route("edit")
public class Edit extends VerticalLayout  implements HasUrlParameter<String> {

    @Autowired
    private PostInterfaceImpl postInterface;

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private ThemeInterface themeInterface;

    private String owner;
    private ArrayList<Post> listPosts = new ArrayList<>();

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
                themeInterface.persist(newTheme);
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

        Button buttonShowPosts = new Button("show your posts");
        buttonShowPosts.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                Iterator<Post> it = postInterface.findAll();
                while(it.hasNext()){
                    Post post = it.next();
                    if(post.getOwner() != null && post.getOwner().equals(owner)){
                        Button edit = new Button("edit");
                        Button submit = new Button("submit change");
                        TextArea textPost = new TextArea();
                        textPost.setPlaceholder("Your text");
                        textPost.setWidth("500px");
                        textPost.setHeight("400px");
                        textPost.setLabel(post.getTitle());
                        textPost.setValue(post.getPost());
                        textPost.setEnabled(false);
                        add(textPost);
                        add(edit);
                        add(submit);
                        listPosts.add(post);
                        edit.addClickListener(e -> {
                            textPost.setEnabled(true);
                        });
                        submit.addClickListener(e -> {
                            textPost.setEnabled(false);
                            Post tmp = new Post();
                            tmp.setPost(textPost.getValue());
                            tmp.setTitle(textPost.getLabel());
                            tmp.setId(post.getId());
                            postInterface.addPost(tmp);
                        });
                    }
                }
            }
        });
        add(labelNewPost);
        add(fieldTheme);
        add(buttonTheme);
        add(fieldChooseTmene);
        add(fieldTitle);
        add(textPost);
        add(newPost);
        add(fieldDeletePost);
        add(deletePost);
        add(buttonShowPosts);
        newPost.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                String title = fieldTitle.getValue();
                String post = textPost.getValue();
                String theme =fieldChooseTmene.getValue();
                Post newPost = new Post();
                if(themeInterface.getPostsByTheme(theme) != null){
                    newPost.setThemename(theme);
                }
                newPost.setTitle(title);
                newPost.setPost(post);
                newPost.setOwner(owner);
                System.out.println(owner);
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
            owner = request.getSession().getAttribute("username").toString();
            if(userInterface.findUserByUsername(username) == null || !userInterface.findUserByUsername(username).isLogin()){
                beforeEvent.getUI().navigate("");
                beforeEvent.getUI().getPage().reload();
            }
        }
    }
}
