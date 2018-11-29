package com.sacidpak.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sacidpak
 */
public interface UserRepository extends JpaRepository <User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
