package com.example.SpringBatchDBtoCsv.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.SpringBatchDBtoCsv.model.Person;

public class Reader implements ItemReader<List<Person>> {

	private JdbcTemplate jdbcTemplate;

	private boolean isExecuted = false;

	public Reader(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Person> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (!isExecuted) {
			System.out.println("READER");
			List<Person> personList = new ArrayList<>();
			jdbcTemplate.query("select first_name, last_name, email, age from person", (rs, rowNum) -> {
				Person person = new Person();
				person.setFirstname(rs.getString("first_name"));
				person.setLastname(rs.getString("last_name"));
				person.setEmail(rs.getString("email"));
				person.setAge(rs.getInt("age"));
				personList.add(person);
				return person;
			});
			System.out.println(personList.toString());
			isExecuted = true;
			return personList;
		} else {
			return null;
		}
	}

}
