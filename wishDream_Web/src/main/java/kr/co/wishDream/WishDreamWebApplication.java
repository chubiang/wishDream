package kr.co.wishDream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import kr.co.wishDream.config.ApplicationWebFluxConfig;
import kr.co.wishDream.config.LocaleConfig;

@SpringBootApplication
@Import({ApplicationWebFluxConfig.class, LocaleConfig.class})
public class WishDreamWebApplication {
	
	public static void main(String[] args) {
		// it does not watch for file changes, before calling SpringApplication.run(…​)
		// If you do not want to start the LiveReload server
		//System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(WishDreamWebApplication.class, args);
		
	}

}
