package com.example.SpringBatchDBtoCsv.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.SpringBatchDBtoCsv.model.Person;

public class PersonrowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setPersonId(rs.getInt("personId"));
		person.setFirstname(rs.getString("firstName"));
		person.setLastname(rs.getString("lastName"));
		person.setEmail(rs.getString("emal"));
		person.setAge(rs.getInt("age"));
		return person;
	}

}
