package com.sacidpak.todolist.list;
import com.sacidpak.todolist.user.User;
import com.sacidpak.todolist.list.TodoListRepositroy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

/**
 * @author sacidpak
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoListRepositoryTests {

    @Autowired
    private TodoListRepositroy todoListRepositroy;

    @Autowired
    private TestEntityManager entityManager;
    private User owner;

    @Before
    public void setUp() throws Exception {
        owner = entityManager.find(User.class, 1L);
    }

    @Test
    public void testCreate() {
        TodoList sacidList = new TodoList("sacidTestList", owner);
        todoListRepositroy.save(sacidList);

        TodoList loadedSacidList = todoListRepositroy.findOneByIdAndOwner(2L,owner);
        assertThat(loadedSacidList.getItems()).isEmpty();
        assertThat(loadedSacidList.getName()).isEqualTo("sacidTestList");
        assertThat(loadedSacidList.getOwner()).isEqualTo(owner);
    }

    @Test
    public void testGet() {
        TodoList todoList = todoListRepositroy.findOneByIdAndOwner(1L, owner);

        assertThat(todoList.getName()).isEqualTo("Test List 1");
    }

    @Test
    public void testList() {
        List<TodoList> lists = todoListRepositroy.findAllByOwner(owner);
        assertThat(lists.size()).isEqualTo(2);
    }

    @Test
    public void testDelete() {
        todoListRepositroy.deleteByIdAndOwner(1L, owner);
        assertNull(todoListRepositroy.findOneByIdAndOwner(1L,owner));
    }
}
