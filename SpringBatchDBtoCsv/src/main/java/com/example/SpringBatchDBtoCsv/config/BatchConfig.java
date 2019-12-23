package com.example.SpringBatchDBtoCsv.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.policy.DefaultResultCompletionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.SpringBatchDBtoCsv.model.Person;
import com.example.SpringBatchDBtoCsv.processor.PersonItemProcessor;
import com.example.SpringBatchDBtoCsv.reader.Reader;
import com.example.SpringBatchDBtoCsv.writer.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Bean
	public Reader reader() throws Exception {
		return new Reader(jdbcTemplate);
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public Writer writer() {

		return new Writer();

	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<List<Person>, List<Person>>chunk(1).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public Job exportPersonJob() throws Exception {
		return jobBuilderFactory.get("exportPersonJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();

	}

}
