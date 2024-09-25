package online.glees.numble;

import org.springframework.boot.SpringApplication;

public class TestNumbleApplication {

	public static void main(String[] args) {
		SpringApplication.from(NumbleApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
