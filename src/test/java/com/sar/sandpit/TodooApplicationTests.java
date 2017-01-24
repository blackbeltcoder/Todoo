package com.sar.sandpit;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodooApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void addTaskItem_success() throws Exception {

		String s ="string";
		//s=null;
		///Assert.assertThat("",);

		assertThat("", s ,is(notNullValue()));




	}
}
