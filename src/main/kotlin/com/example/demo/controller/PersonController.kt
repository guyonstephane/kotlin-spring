package com.example.demo.controller
import com.example.demo.repository.ArticleRepository
import com.example.demo.repository.PersonRepository
import com.example.demo.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class PersonController @Autowired constructor(private val personRepository: PersonRepository) {

    @GetMapping("/auteurs")
    fun index(model: Model): String {
        model["title"] = "Les auteurs"
        model["auteurs"] = personRepository.findAll()
        println(model)
        return "auteurs/index"
    }
}