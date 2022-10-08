package tacos;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;


@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer{
	
	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/taco_repo");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		
		return dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args)throws Exception{
				repo.save(new Ingredient("FLTO","Flour Tortilla", Type.WRAP));
				repo.save(new Ingredient("COTO","Corn Tortilla", Type.WRAP));
				repo.save(new Ingredient("GRBF","Ground Beaf", Type.PROTEIN));
				repo.save(new Ingredient("CARN","Carntias", Type.PROTEIN));
				repo.save(new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("LECT","Lettuce", Type.VEGGIES));
				repo.save(new Ingredient("CHED","Cheddar", Type.CHEESE));
				repo.save(new Ingredient("JACK","Monterrey", Type.CHEESE));
				repo.save(new Ingredient("SLSA","Salsa", Type.SAUCE));
				repo.save(new Ingredient("SRCR","Sour Cream", Type.SAUCE));
			}
		};
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

}
