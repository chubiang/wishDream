package kr.co.wishDream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplicationWebFluxConfig.class},
				properties = {"classpath:application-test.yml"})
public class WishDreamCoreApplicationTests {
	
	@Test
	public void contextLoads() {
		
	}

}
