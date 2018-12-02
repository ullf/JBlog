package ru.marksblog.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marksblog.controller.PostInterfaceImpl;
import ru.marksblog.entity.Post;
import ru.marksblog.entity.Theme;
import ru.marksblog.repository.ThemeRepository;
import ru.marksblog.service.ThemeService;

import java.util.ArrayList;
import java.util.Iterator;

@Route("blog")
public class Blog extends VerticalLayout implements BeforeEnterObserver,AfterNavigationObserver{

    private ListBox<String> listThemes = new ListBox<>();
    private ArrayList<TextField> textFields = new ArrayList<>();

    @Autowired
    private PostInterfaceImpl postInterface;

    @Autowired
    private ThemeService themeService;

    public Blog() {
        super(new Menu());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Iterator<Post> iter = postInterface.findAll();
        Iterator<Theme> iter2 = themeService.findAll();
        ArrayList<String> list = new ArrayList<>();
        while(iter2.hasNext()){
            list.add(iter2.next().getThemename());
        }
        list.add("all");
        listThemes.setItems(list);
        add(listThemes);
        while (iter.hasNext()) {
            Post post = iter.next();
            TextField areaPost = new TextField();
            areaPost.setWidth("500px");
            areaPost.setLabel(post.getTitle());
            areaPost.setValue(post.getPost());
            add(areaPost);
            textFields.add(areaPost);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        listThemes.addValueChangeListener(event -> {
            Iterator<TextField> it = textFields.iterator();
            Iterator<Post> iter = postInterface.findAll();
            while (it.hasNext() && iter.hasNext()) {
                Post post = iter.next();
                if(event.getValue().equals("all")){
                    it.next().setVisible(true);
                } else {
                    if(!event.getValue().equals(post.getThemename())){
                        it.next().setVisible(false);
                    } else {
                        it.next().setVisible(true);
                    }
                }
            }
        });
    }
}
