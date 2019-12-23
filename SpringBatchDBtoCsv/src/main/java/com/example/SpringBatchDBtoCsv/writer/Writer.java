package com.example.SpringBatchDBtoCsv.writer;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import com.example.SpringBatchDBtoCsv.model.Person;

public class Writer implements ItemWriter<List<Person>> {

	ExecutionContext executionContext;

	@Override
	public void write(List<? extends List<Person>> persons) throws Exception {

		FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
		writer.setResource(new FileSystemResource(
				"C:\\Users\\sndani\\Downloads\\SpringBatchDBtoCsv\\src\\main\\resources\\persons.csv"));
		writer.open(executionContext);
		DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<Person>();
		lineAggregator.setDelimiter("|");
		BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<Person>();
		fieldExtractor.setNames(new String[] { "firstname", "lastname", "email", "age" });
		lineAggregator.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(lineAggregator);
		System.out.println("========persons========"+persons.get(0));
		writer.write(persons.get(0));
		writer.close();
	}

	/*
	 * public Writer() {
	 * 
	 * super.setResource(new FileSystemResource( new File(
	 * "C:\\Users\\sndani\\Downloads\\SpringBatchDBtoCsv\\src\\main\\resources\\persons.csv"
	 * )));
	 * 
	 * DelimitedLineAggregator<Person> lineAggregator = new
	 * DelimitedLineAggregator<Person>(); lineAggregator.setDelimiter(",");
	 * BeanWrapperFieldExtractor<Person> fieldExtractor = new
	 * BeanWrapperFieldExtractor<Person>(); fieldExtractor.setNames(new String[] {
	 * " firstname", "lastname", "email", "age" });
	 * lineAggregator.setFieldExtractor(fieldExtractor); super.close(); }
	 */
	
	
	  @BeforeStep public void beforeSteps(StepExecution stepExecution) {
	  executionContext = stepExecution.getExecutionContext(); }
	 

}