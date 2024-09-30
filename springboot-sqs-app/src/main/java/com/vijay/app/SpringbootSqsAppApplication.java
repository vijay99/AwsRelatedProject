package com.vijay.app;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringbootSqsAppApplication {

	@Autowired
	SqsTemplate sqsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSqsAppApplication.class, args);
	}

	@GetMapping("send/{message}")
	public void send(@PathVariable String message) {
		sqsTemplate.send("https://sqs.us-east-1.amazonaws.com/140023408648/MySQS", message);
	}

	@SqsListener("MySQS")
	public void receive(String message) {
		System.out.println("message is:" + message);
	}

}
