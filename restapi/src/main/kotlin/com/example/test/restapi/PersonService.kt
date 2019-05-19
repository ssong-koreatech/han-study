package com.example.test.restapi

interface PersonService {
    fun getPerson(id: Int) : Person?
    fun createPerson(person: Person)
    fun deletePerson(id: Int)
    fun updatePerson(id: Int, person: Person)
    fun searchPerson(nameFilter: String): List<Person>
}