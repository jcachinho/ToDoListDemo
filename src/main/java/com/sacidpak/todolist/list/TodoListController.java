package com.sacidpak.todolist.list;

import com.sacidpak.todolist.item.TodoItem;
import com.sacidpak.todolist.item.TodoItemCreatedResponse;
import com.sacidpak.todolist.item.TodoItemRepository;
import com.sacidpak.todolist.item.TodoItemRequest;
import com.sacidpak.todolist.user.User;
import com.sacidpak.todolist.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sacidpak
 */
@RestController
public class TodoListController {

    @Autowired
    private TodoListRepositroy todoListRepositroy;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/lists")
    public ResponseEntity<TodoListCreatedResponse> create(@RequestBody TodoListRequest todoListRequest,
                                                          Authentication authentication) {
        TodoList todoList = todoListRepositroy.save(TodoList.from(todoListRequest, getOwnerFromAuthentication(authentication)));
        return new ResponseEntity<>(TodoListCreatedResponse.from(todoList), HttpStatus.CREATED);
    }

    @PostMapping("/lists/{id}/items")
    public ResponseEntity<TodoItemCreatedResponse> createItem(@PathVariable("id") Long id, @RequestBody TodoItemRequest todoItemRequest,
                                                              Authentication authentication) {
        TodoList todoList = todoListRepositroy.findOneByIdAndOwner(id, getOwnerFromAuthentication(authentication));
        TodoItem todoItem = todoItemRepository.save(TodoItem.from(todoItemRequest, todoList));
        return new ResponseEntity<>(TodoItemCreatedResponse.from(todoItem), HttpStatus.CREATED);
    }

    @GetMapping("/lists")
    public ResponseEntity<Iterable<TodoList>> list(Authentication authentication) {
        List<TodoList> allByOwner = todoListRepositroy.findAllByOwner(getOwnerFromAuthentication(authentication));
        return new ResponseEntity<>(allByOwner, HttpStatus.OK);
    }

    @GetMapping("/lists/{id}")
    public ResponseEntity<TodoList> get(@PathVariable("id") Long id,
                                        Authentication authentication) {
        return new ResponseEntity<>(todoListRepositroy.findOneByIdAndOwner(id, getOwnerFromAuthentication(authentication)), HttpStatus.OK);
    }

    @DeleteMapping("/lists/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id,
                                         Authentication authentication) {
        todoListRepositroy.deleteByIdAndOwner(id, getOwnerFromAuthentication(authentication));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/lists/{id}/items/{itemId}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id,
                                         @PathVariable("itemId") Long itemId,
                                         Authentication authentication) {
        TodoList todoList = todoListRepositroy.findOneByIdAndOwner(id, getOwnerFromAuthentication(authentication));
        todoItemRepository.deleteByIdAndListAndOwner(itemId, todoList, getOwnerFromAuthentication(authentication));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/lists/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id,
                                         @RequestBody TodoListRequest request,
                                         Authentication authentication) {
        TodoList todoList = todoListRepositroy.findOneByIdAndOwner(id, getOwnerFromAuthentication(authentication));
        todoList.merge(request);
        todoListRepositroy.save(todoList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/lists/{id}/items/{itemId}")
    public ResponseEntity<String> updateItem(@PathVariable("id") Long id,
                                             @PathVariable("itemId") Long itemId,
                                             @RequestBody TodoItemRequest request,
                                             Authentication authentication) {
        TodoList todoList = todoListRepositroy.findOneByIdAndOwner(id, getOwnerFromAuthentication(authentication));
        TodoItem todoItem = todoItemRepository.findOneByIdAndListAndOwner(itemId, todoList, getOwnerFromAuthentication(authentication));
        todoItem.merge(request);
        todoItemRepository.save(todoItem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private User getOwnerFromAuthentication(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return user;
    }
}
