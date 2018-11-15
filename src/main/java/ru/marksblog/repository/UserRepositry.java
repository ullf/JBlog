package ru.marksblog.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marksblog.entity.User;

@Repository
@Transactional
public interface UserRepositry extends CrudRepository<User,Long> {

    @Query("DELETE FROM User user WHERE user.username=:username")
    @Modifying
    void deleteByUsername(@Param("username") String username);

    @Query("SELECT user FROM User user WHERE user.username=:username and user.password=:password")
    User loginUser(@Param("username") String username,@Param("password") String password);

    @Query("UPDATE User user SET user.isLogin=true WHERE user.username=:username and user.password=:password")
    @Modifying
    void setLogin(@Param("username") String username,@Param("password") String password);
}
