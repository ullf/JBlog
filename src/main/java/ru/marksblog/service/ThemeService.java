package ru.marksblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.Post;
import ru.marksblog.entity.Theme;
import ru.marksblog.repository.ThemeRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public Iterator<Theme> getPostsByTheme(String themename) {
        return themeRepository.getPostsByTheme(themename).iterator();
    }

    public Iterator<Theme> findAll(){
        return themeRepository.findAll().iterator();
    }

    public void persist(Theme theme){
        themeRepository.save(theme);
    }
}
