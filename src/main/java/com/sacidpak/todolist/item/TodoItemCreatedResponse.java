package com.sacidpak.todolist.item;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sacidpak
 */
@Data
@NoArgsConstructor
public class TodoItemCreatedResponse {
    private String id;
    private String description;
    private String deadline;
    private String name;
    private String prevItemId;

    public TodoItemCreatedResponse(String id, String description, String deadline, String name, String prevItemId) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.name = name;
        this.prevItemId = prevItemId;
    }

    public static TodoItemCreatedResponse from(TodoItem todoItem) {

        return new TodoItemCreatedResponse(todoItem.getId().toString(),
                                           todoItem.getDescription(),
                                           todoItem.getDeadline(),
                                           todoItem.getName(),
                                           todoItem.getPrevItemId());
    }
}
