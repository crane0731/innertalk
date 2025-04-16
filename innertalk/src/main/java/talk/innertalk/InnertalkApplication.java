package talk.innertalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
//@MapperScan("talk.innertalk.repository.mybatis")
public class InnertalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnertalkApplication.class, args);
	}

}
