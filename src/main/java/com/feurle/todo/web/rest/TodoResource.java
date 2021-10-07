package com.feurle.todo.web.rest;

import com.feurle.todo.model.Todo;
import com.feurle.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    // set to private final to instantiate the repository in the constructor
    private final TodoRepository todoRepository;

    /**
     * To define a logger is a common use case to output some information into the logfile
     */
    private final Logger log = LoggerFactory.getLogger(TodoResource.class);

    /**
     * Using the constructor is another way (and preferred way) to inject the repository in this class.
     * Another option is not to use the constructor - instead you can do that with the @Autowired annotation.
     *
     * @param todoRepository - the repository to inject/instantiate into this controller class.
     */
    public TodoResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * {@code GET /todos} : get all todoItems with all details.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all todoItems.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        log.info("REST request to get all todos");
        List<Todo> todos = todoRepository.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * {@code GET /todos/:id} : get the todoItem.
     *
     * @param id the id of the todoItem to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the todoItem
     */
    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") Long id) {
        log.info("REST request to get todo with id: {}",id);
        Todo todo = todoRepository.getById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    /**
     * {@code POST /todos} : Creates a new todoItem.
     *
     * @param todoItem the todoItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new todo.
     */
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todoItem) {
        log.info("REST request to save todo: {}", todoItem);
        Todo savedTodo = todoRepository.save(todoItem);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    /**
     * {@code PUT /todos} : Updates an existing todoItem.
     *
     * @param todoItem the todoItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated todoItem.
     */
    @PutMapping("/todos")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todoItem) {
        log.info("REST request to update todo: {}", todoItem);
        Todo savedTodo = todoRepository.save(todoItem);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }

    /**
     * {@code DELETE /todos/:id} : delete a todoItem.
     *
     * @param id the id of the todoItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
        log.info("REST request to delete todo with id: {}", id);
        todoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
