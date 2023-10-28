package com.example.demo.controller

import com.example.demo.repository.ArticleRepository
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
class ArticleController @Autowired constructor(private val articleRepository: ArticleRepository){

    @GetMapping("/articles")
    fun index(model: Model): String {
        model["title"] = "Les articles"
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc()
        println(model)
        return "article/index"
    }

    @GetMapping("/article/{id}")
    fun details(model: Model, @PathVariable id: Long) : String {
        println("coucou")
        println(id)
        //var id = argument.toLong()
        model["title"] = "Article"
        model["article"] = articleRepository.findById(id)
        println(model)
        return "article/details"
    }

}