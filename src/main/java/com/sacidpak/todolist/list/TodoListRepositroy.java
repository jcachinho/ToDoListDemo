package com.sacidpak.todolist.list;

import com.sacidpak.todolist.user.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sacidpak
 */
public interface TodoListRepositroy extends CrudRepository<TodoList,Long> {
    List<TodoList> findAllByOwner(User owner);

    TodoList findOneByIdAndOwner(Long id, User owner);

    @Transactional
    void deleteByIdAndOwner(Long id, User owner);
}
