package com.example.SpringBatchDBtoCsv.processor;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.example.SpringBatchDBtoCsv.model.Person;

public class PersonItemProcessor implements ItemProcessor<List<Person>, List<Person>> {

	@Override
	public List<Person> process(List<Person> persons) throws Exception {
		// TODO Auto-generated method stub
		return persons;
	}

}
