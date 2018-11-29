insert into role(id,name) values (1, 'ADMIN');

insert into user(id, username, password) values (1, 'sacidpak', 'password');


insert into user_role(user_id, role_id) values (1, 1);

insert into todo_list(id, name, owner_user_id) values (1, 'Test List 1', 1);

insert into todo_item(id, name, todo_list_id, description, deadline, status, prev_item_id, owner_user_id) values (1, 'Item 1', 1, 'test1', '30/11/2018', FALSE, '*', 1);
insert into todo_item(id, name, todo_list_id, description, deadline, status, prev_item_id, owner_user_id) values (2, 'Item 2', 1, 'test2', '30/11/2018', FALSE, '1', 1);
insert into todo_item(id, name, todo_list_id, description, deadline, status, prev_item_id, owner_user_id) values (3, 'Item 3', 1, 'test3', '30/11/2018', FALSE, '2', 1);
