package com.example.workflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.camunda.consulting.simulator.PayloadGenerator;
import com.camunda.consulting.simulator.SimulationExecutor;
import com.camunda.consulting.simulator.SimulatorPlugin;

@SpringBootApplication
@EnableProcessApplication("camunda-example")
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);

    SimulationExecutor.execute(DateTime.now().minusMonths(5).toDate(), DateTime.now().toDate());
  }

  @Bean
  public SimulatorPlugin simulatorPlugin() {
      return new SimulatorPlugin();
  }

    /**
   * Makes the PayloadGenerator available in expressions and scripts.
   */
  @Bean
  public PayloadGenerator generator() {
    return new PayloadGenerator();
  }

}