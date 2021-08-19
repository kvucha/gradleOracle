package com.freeCode.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.freeCode.model.Person;

public class PersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
		 
		 person.setId(rs.getInt("ID"));
		 person.setName(rs.getString("NAME"));
		 person.setCourse(rs.getString("COURSE"));
         return person;
	}

}
