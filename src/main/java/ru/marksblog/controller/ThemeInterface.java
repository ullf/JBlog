package ru.marksblog.controller;

import org.springframework.stereotype.Repository;
import ru.marksblog.entity.Post;
import ru.marksblog.entity.Theme;
import java.util.Iterator;

@Repository
public interface ThemeInterface {

    Iterator<Theme> getPostsByTheme(String themename);
    Iterator<Theme> findAll();
    void persist(Theme theme);
}
