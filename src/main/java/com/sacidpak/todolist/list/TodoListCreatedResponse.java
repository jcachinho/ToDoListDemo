package com.sacidpak.todolist.list;

import com.sacidpak.todolist.item.TodoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sacidpak
 */
public class TodoListCreatedResponse {
    private String id;
    private String name;
    private List<TodoItem> items = new ArrayList<>();

    public TodoListCreatedResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TodoListCreatedResponse from(TodoList todoList) {
        return new TodoListCreatedResponse(todoList.getId().toString(), todoList.getName());
    }
}
