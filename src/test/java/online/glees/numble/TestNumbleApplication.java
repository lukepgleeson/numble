package online.glees.numble;

import org.springframework.boot.SpringApplication;

import online.glees.numble.application.Test;

public class TestNumbleApplication {

	public static void main(String[] args) {
//		SpringApplication.from(NumbleApplication::main).with(TestcontainersConfiguration.class).run(args);
    Test test = new Test();    
    test.setValue("Value");
    System.out.println(test.getValue());
	}

}
