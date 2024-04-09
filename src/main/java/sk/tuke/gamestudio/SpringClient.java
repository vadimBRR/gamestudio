package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType	.REGEX,
		pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
	}
	@Bean
	public CommandLineRunner runner(ConsoleUI consoleUI) {
		return args -> consoleUI.play();
	}
	@Bean
	public ConsoleUI consoleUI(Field field) {
		return new ConsoleUI(field);
	}

	@Bean
	public Field field() {
		return new Field(4);
	}

	@Bean
	public ScoreService scoreService() {
		return new ScoreServiceRestClient();
	}
	@Bean
	public CommentService commentService() {
		return new CommentServiceRestClient();
	}
	@Bean
	public RatingService ratingService() {
		return new RatingServiceRestClient();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
