package com.sacidpak.todolist.item;

import com.sacidpak.todolist.list.TodoList;
import com.sacidpak.todolist.user.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * @author sacidpak
 */
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
    TodoItem findOneByIdAndListAndOwner(Long id, TodoList todoList, User owner);

    @Transactional
    void deleteByIdAndListAndOwner(Long id, TodoList todoList, User owner);
}
