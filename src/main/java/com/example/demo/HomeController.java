package com.example.demo;


import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController{
    @Autowired
    JobRepository TodoRepository;
    @RequestMapping("/")
    public String listJobs(Model model) {
        model.addAttribute("todos", TodoRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String TodoForm(Model model){
        model.addAttribute("todo", new todos());
        return "todoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Todo todo,
                              BindingResult result){


        if(result.hasErrors()){
            return "Todoform";
        }
        TodoRepository.save(todo);
        return "redirect:/";
    }
}
