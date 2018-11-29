package com.sacidpak.todolist.user;

/**
 * @author sacidpak
 */

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
}
