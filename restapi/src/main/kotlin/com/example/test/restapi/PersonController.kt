package com.example.test.restapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class PersonController {
    @Autowired
    private lateinit var personService: PersonService

    /*
    @GetMapping(value=["/person/{id}"])
    fun getPerson(@PathVariable id: Int) {
        ResponseEntity(personService.getPerson(id), HttpStatus.OK)
    }
     */

    @GetMapping(value=["/person/{id}"])
    fun getPerson(@PathVariable id: Int) : ResponseEntity<Person?> {
        val person = personService.getPerson(id) ?:
        throw PersonNotFoundException("person '$id' not found")
        return ResponseEntity(person, HttpStatus.OK)
        //val status = if(person == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        //return ResponseEntity(person, status)
    }

    @PostMapping(value=["/person/"])
    fun createPerson(@RequestBody person: Person){
        personService.createPerson(person)
    }

    @DeleteMapping(value=["/person/{id}"])
    fun deletePerson(@PathVariable id: Int){
        personService.deletePerson(id)
    }

    @PutMapping(value=["/person/{id}"])
    fun updatePerson(@PathVariable id: Int, @RequestBody person: Person){
        personService.updatePerson(id, person)
    }

    @GetMapping(value=["/persons"])
    fun getPersons(@RequestParam(required=false, defaultValue = "") nameFilter: String)
            = personService.searchPerson(nameFilter)
}