package ru.marksblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.Post;
import ru.marksblog.entity.Theme;
import ru.marksblog.service.ThemeService;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

@Service
public class ThemeInterfaceImpl implements ThemeInterface {

    @Autowired
    private ThemeService themeService;
    @Override
    public Iterator<Theme> getPostsByTheme(String themename) {
       return themeService.getPostsByTheme(themename);
    }

    @Override
    public Iterator<Theme> findAll() {
        return themeService.findAll();
    }

    public void persist(Theme theme){
        themeService.persist(theme);
    }
}
