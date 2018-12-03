package ru.marksblog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marksblog.entity.Theme;

@Repository
@Transactional
public interface ThemeRepository extends CrudRepository<Theme,Long>{

    @Query("SELECT post FROM Post post WHERE post.themename=:themename")
    Iterable<Theme> getPostsByTheme(@Param("themename") String themename);
    @Query("SELECT theme FROM Theme theme")
    Iterable<Theme> findAll();
}
