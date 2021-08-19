/*
 * short cuts
 * ctrl + alt + downarrow, copies a line and pastesdown
 * 
 */

package com.freeCode.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.freeCode.model.Person;


public interface PersonDao {

	void insertPerson(Person person);
	List<Person> getAllPersons();
	void updatePerson(Person person);
	void removePersonByID(int id);
	Person getPersonByID(int id);

}
