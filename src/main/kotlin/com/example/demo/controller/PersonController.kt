package com.example.demo.controller
import com.example.demo.domain.Person
import com.example.demo.dto.PersonDto
import com.example.demo.repository.ArticleRepository
import com.example.demo.repository.PersonRepository
import com.example.demo.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
class PersonController @Autowired constructor(private val personRepository: PersonRepository) {

    @GetMapping("/auteurs")
    fun index(model: Model): String {
        model["title"] = "Les auteurs"
        model["auteurs"] = personRepository.findAll()
        println(model)
        return "auteurs/index"
    }

    @GetMapping("/addPerson")
    fun showForm(personDto: PersonDto): String {
        return "auteurs/form"
    }

    @PostMapping("/addPerson")
    fun addPerson(@Valid personDto: PersonDto,
    bindingResult: BindingResult): String {
        return if (bindingResult.hasErrors()) {
            "auteurs/form"
        } else {
           var login = personDto.login
            var first = personDto.firstname
            var last = personDto.lastname
            var des = personDto.description

            personRepository.save(Person(login,first,last,des))
            "redirect:/auteurs"
        }
    }
}