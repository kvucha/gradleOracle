package com.freeCode.api;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//They call api as Controller
import com.freeCode.model.Person;
import com.freeCode.service.PersonService;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

@Autowired	
private PersonService personService;

@RequestMapping(method = RequestMethod.GET )
public List<Person> getAllPersons() {
	return personService.getAllPersons();
}


@GetMapping(path ="{id}")
public Person getPersonById(@PathVariable("id") int id) {
	return personService.getPersonByID(id);
}

@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
public void updatePerson(@RequestBody Person person) {
	personService.updatePerson(person);
}



@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
public void insertPerson(@RequestBody Person person) {
	personService.insertPerson(person);
}


@RequestMapping(path ="{id}",method = RequestMethod.DELETE)
public void deletePersonById(@PathVariable("id") int id) {
	personService.removePersonById(id);
}






}
