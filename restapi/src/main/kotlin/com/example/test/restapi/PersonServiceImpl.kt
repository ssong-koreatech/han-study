package com.example.test.restapi

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PersonServiceImpl : PersonService {
    companion object {
        val initialPersons = arrayOf(Person(1, "han", Person.Telephone("+82", "01011111111")),
                Person(2, "yu", Person.Telephone("+82", "01000000000")),
                Person(3, "Gyeong", Person.Telephone("+82", "01022222222")),
                Person(4, "james"))
    }
    val persons = ConcurrentHashMap<Int, Person>(initialPersons.associateBy(Person::id))


    override fun getPerson(id: Int): Person? = persons[id]

    override fun createPerson(person: Person){
        persons[person.id] = person
    }

    override fun deletePerson(id: Int) {
        persons.remove(id)
    }

    override fun updatePerson(id: Int, person: Person) {
        deletePerson(id)
        createPerson(person)
    }

    override fun searchPerson(nameFilter: String): List<Person> =
            persons.filter{
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Person>::value).toList()
}