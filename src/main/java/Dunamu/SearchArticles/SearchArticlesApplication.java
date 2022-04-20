package Dunamu.SearchArticles;

import Dunamu.SearchArticles.controller.SearchArticlesController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SearchArticlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchArticlesApplication.class, args);
	}

}
