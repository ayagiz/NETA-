package com.netas.project;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.netas.project.HSQLDB.HSQLDB;

@SpringBootTest

class ProjectApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Testing-------------------------------------------------------------------");
	}

}
