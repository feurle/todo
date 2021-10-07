package com.feurle.todo;

import com.feurle.todo.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoApplicationTests {

	@Autowired
	TodoRepository todoRepository;

	@Test
	void todoRepository() {
		assertThat(todoRepository.count()).isEqualTo(0);
	}

}
