package com.sacidpak.todolist.list;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sacidpak
 */
@Data
@NoArgsConstructor
public class TodoListRequest {

    private String name;

    public TodoListRequest(String name) {
        this.name = name;
    }

}

