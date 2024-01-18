package com.example.demospring1.controller;

import com.example.demospring1.model.Employee;
import com.example.demospring1.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    IEmployeeRepository iEmployeeRepository;
//    @GetMapping
//    public ModelAndView showAll(){
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("list",iEmployeeRepository.findAll());
//        return modelAndView;
//    }
    @GetMapping("")
    public ModelAndView getAllPageable(@RequestParam(defaultValue = "0") int currentPage) {
        Page<Employee> listPage = iEmployeeRepository.findAll(PageRequest.of(currentPage, 3));
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list", listPage);
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView showFormAdd(){
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee){
        iEmployeeRepository.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("employee", iEmployeeRepository.findById(id));
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        iEmployeeRepository.deleteById(id);
        return "redirect:/employees";
    }
    @GetMapping("/search")
    public ModelAndView searchEmployee(@RequestParam String name){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list" ,iEmployeeRepository.findAllByNameContaining(name));
        return modelAndView;
    }
    @GetMapping("/searchSalary")
    public ModelAndView searchSalary(@RequestParam Double minSalary, @RequestParam Double maxSalary) {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list", iEmployeeRepository.findAllBySalaryBetween(minSalary, maxSalary));
        return modelAndView;
    }



}
