package kr.co.wishDream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDatabaseConnect.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class WishDreamWebApplicationTests {

	
	
	@Test
	public void contextLoads() {
	}

	
	@TestConfiguration
	public static class TestConfig {
		
	}
}
