package com.freeCode.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.freeCode.model.Person;

@Repository
@Qualifier("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
   private static List<Person> DB = new ArrayList<>();
	@Override
	public void insertPerson(Person person) {
		DB.add(person);
	}

	@Override
	public List<Person> getAllPersons() {
		return DB;
	}

	@Override
	public void updatePerson(Person person) {
        for (int i = 0; i < DB.size(); i++) {
			if (DB.get(i).getId() == person.getId()) {
				 DB.get(i).setCourse( person.getCourse());
				 DB.get(i).setName( person.getName());
				 break;
			}
        }
		
	}

	@Override
	public void removePersonByID(int id) {
		
	       for (int i = 0; i < DB.size(); i++) {
				if (DB.get(i).getId() == id) {
					  DB.remove(i);
				}
	        }
		
	}

	@Override
	public Person getPersonByID(int id) {
   	       Person p = null;
	       for (int i = 0; i < DB.size(); i++) {
				if (DB.get(i).getId() == id) {
					 p = DB.get(i);
					 break;
				}
	        }
	       return p;
	}

}
