package com.sacidpak.todolist.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sacidpak
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoItemRequest {

    private String name;
    private String description;
    private String deadline;
    private boolean status;
    private String prevItemId;


    public TodoItemRequest(String name) {
        this.name = name;
    }
}
