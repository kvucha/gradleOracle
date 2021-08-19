package com.freeCode.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.freeCode.dao.PersonDao;
import com.freeCode.model.Person;


@Service
public class PersonService {

	private PersonDao personDao;
	
	@Autowired
	public PersonService(@Qualifier("oracleDao") PersonDao personDao) {
	//public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
		this.personDao = personDao;
	}
	
	
	public List<Person> getAllPersons() {
		return this.personDao.getAllPersons();
	}
	
	
	

	public Person getPersonByID(int id) {
		return this.personDao.getPersonByID(id);
	}



	public void removePersonById(int id) {
		this.personDao.removePersonByID(id);
		
	}
	
	
	public void updatePerson(Person person) {
	    this.personDao.updatePerson(person);
	}



	public void insertPerson(Person person) {
		 this.personDao.insertPerson(person);
		
	}

}
